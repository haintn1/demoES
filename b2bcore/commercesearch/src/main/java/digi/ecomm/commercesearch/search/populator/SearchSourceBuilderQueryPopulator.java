package digi.ecomm.commercesearch.search.populator;

import digi.ecomm.commercesearch.data.IndexedPropertyGroup;
import digi.ecomm.commercesearch.provider.FieldNameResolver;
import digi.ecomm.commercesearch.search.data.FilterQueryOperator;
import digi.ecomm.commercesearch.search.data.SearchFilterQueryData;
import digi.ecomm.commercesearch.search.data.SearchQueryPageableData;
import digi.ecomm.commercesearch.search.data.SearchQueryTermData;
import digi.ecomm.commercesearch.strategy.EsFilterQueryBuilderFactory;
import digi.ecomm.commercesearch.strategy.EsIndexedPropertyGroupStrategy;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.entity.commercesearch.EsIndexedPropertyFacetType;
import digi.ecomm.entity.commercesearch.EsPropertyType;
import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SearchSourceBuilderQueryPopulator implements Populator<SearchQueryPageableData, SearchSourceBuilder> {

    private final EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy;
    private final EsFilterQueryBuilderFactory filterQueryBuilderFactory;
    private final FieldNameResolver fieldNameResolver;

    @Override
    public void populate(final SearchQueryPageableData source, final SearchSourceBuilder target) throws ConversionException {
        ServicesUtils.validateParameterNotNullStandardMessage("source", source);
        ServicesUtils.validateParameterNotNullStandardMessage("searchQueryData", source.getSearchQueryData());

        final List<EsIndexedProperty> indexedProperties = source.getContext().getIndexedProperties();
        final BoolQueryBuilder compoundFtsQuery = createCompoundFtsQuery(source.getSearchQueryData().getFreeTextSearch(),
                indexedProperties);
        final QueryBuilder facetFilter = createFacetFilter(indexedProperties, source.getSearchQueryData().getFilterTerms());
        if (Objects.nonNull(facetFilter)) {
            compoundFtsQuery.filter(facetFilter);
        }

        populateFieldsInResponse(target, indexedProperties);
        target.query(compoundFtsQuery);
    }

    private BoolQueryBuilder createCompoundFtsQuery(final String freeTextSearch, final List<EsIndexedProperty> indexedProperties) {
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isBlank(freeTextSearch) || CollectionUtils.isEmpty(indexedProperties)) {
            boolQueryBuilder.must(QueryBuilders.matchAllQuery());
        } else {
            final String[] fieldNames = indexedProperties.stream().filter(property -> BooleanUtils.isTrue(property.isFtsQuery()))
                    .map(EsIndexedProperty::getName).toArray(String[]::new);
            final MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(freeTextSearch, fieldNames);

            multiMatchQueryBuilder.fields(indexedProperties.stream().filter(property -> BooleanUtils.isTrue(property.isFtsQuery()))
                    .filter(property -> Objects.nonNull(property.getFtsQueryBoost()))
                    .collect(Collectors.toMap(EsIndexedProperty::getName, EsIndexedProperty::getFtsQueryBoost)));

            final OptionalDouble maxFuzziness = indexedProperties.stream()
                    .filter(property -> BooleanUtils.isTrue(property.isFtsQuery()))
                    .filter(property -> BooleanUtils.isTrue(property.isFtsFuzzyQuery()))
                    .filter(property -> Objects.nonNull(property.getFtsFuzzyQueryFuzziness()))
                    .mapToDouble(EsIndexedProperty::getFtsFuzzyQueryFuzziness).max();

            if (maxFuzziness.isPresent()) {
                multiMatchQueryBuilder.fuzziness(maxFuzziness.getAsDouble());
            } else {
                multiMatchQueryBuilder.fuzziness(Fuzziness.AUTO);
            }

            boolQueryBuilder.must(multiMatchQueryBuilder);
        }
        return boolQueryBuilder;
    }

    private QueryBuilder createFacetFilter(final List<EsIndexedProperty> indexedProperties,
                                           final List<SearchQueryTermData> filterTerms) {

        final Map<String, SearchFilterQueryData> facetFilterQueriesMap = buildFacetFilterQueries(indexedProperties, filterTerms)
                .stream().collect(Collectors.toMap(SearchFilterQueryData::getKey, Function.identity()));
        final List<IndexedPropertyGroup> indexedPropertyGroup = indexedPropertyGroupStrategy.group(indexedProperties);

        if (CollectionUtils.isNotEmpty(indexedPropertyGroup) && MapUtils.isNotEmpty(facetFilterQueriesMap)) {
            final BoolQueryBuilder compoundFacetFilterQueryBuilder = QueryBuilders.boolQuery();

            // Filter on the field itself
            indexedPropertyGroup.stream().map(IndexedPropertyGroup::getProperty)
                    .filter(facetedProperty -> BooleanUtils.isTrue(facetedProperty.isFacet()))
                    .filter(facetedProperty -> !Objects.equals(facetedProperty.getType(), EsPropertyType.NESTED))
                    .filter(facetedProperty -> facetFilterQueriesMap.containsKey(facetedProperty.getName())).forEach(
                    facetedProperty -> compoundFacetFilterQueryBuilder
                            .must(filterQueryBuilderFactory.get(facetedProperty, facetFilterQueriesMap.get(facetedProperty.getName()))));

            // Filter on the nested field
            indexedPropertyGroup.stream().flatMap(nestedFacetedProperty -> nestedFacetedProperty.getNestedProperties().stream())
                    .filter(nestedFacetedProperty -> BooleanUtils.isTrue(nestedFacetedProperty.isFacet()))
                    .filter(nestedFacetedProperty -> facetFilterQueriesMap.containsKey(nestedFacetedProperty.getName())).forEach(
                    nestedFacetedProperty -> compoundFacetFilterQueryBuilder.must(QueryBuilders
                            .nestedQuery(nestedFacetedProperty.getOuterProperty().getName(), filterQueryBuilderFactory
                                            .get(nestedFacetedProperty, facetFilterQueriesMap.get(nestedFacetedProperty.getName())),
                                    ScoreMode.None)));

            return compoundFacetFilterQueryBuilder;
        }

        return null;
    }

    private List<SearchFilterQueryData> buildFacetFilterQueries(final List<EsIndexedProperty> indexedProperties,
                                                                final List<SearchQueryTermData> filters) {
        final List<SearchFilterQueryData> filterQueries = new ArrayList<>();
        final Map<String, EsIndexedProperty> facetedPropertiesMap = indexedProperties.stream()
                .filter(property -> BooleanUtils.isTrue(property.isFacet()))
                .collect(Collectors.toMap(EsIndexedProperty::getName, Function.identity()));

        filters.stream().filter(filter -> facetedPropertiesMap.containsKey(filter.getKey()))
                .collect(Collectors.groupingBy(SearchQueryTermData::getKey)).forEach((key, values) -> {
            if (CollectionUtils.isNotEmpty(values)) {
                final SearchFilterQueryData filterQuery = new SearchFilterQueryData();
                filterQuery.setKey(key);
                final EsIndexedProperty facetedProperty = facetedPropertiesMap.get(key);

                if (Objects.isNull(facetedProperty.getFacetType()) || Objects
                        .equals(EsIndexedPropertyFacetType.REFINE, facetedProperty.getFacetType())) {
                    filterQuery.setValues(Collections.singleton(values.stream().map(SearchQueryTermData::getValue).findFirst().get()));
                    filterQuery.setOperator(FilterQueryOperator.AND);

                } else if (Objects.equals(EsIndexedPropertyFacetType.MULTISELECT_AND, facetedProperty.getFacetType())) {
                    filterQuery.setValues(values.stream().map(SearchQueryTermData::getValue).collect(Collectors.toSet()));
                    filterQuery.setOperator(FilterQueryOperator.AND);

                } else if (Objects.equals(EsIndexedPropertyFacetType.MULTISELECT_OR, facetedProperty.getFacetType())) {
                    filterQuery.setValues(values.stream().map(SearchQueryTermData::getValue).collect(Collectors.toSet()));
                    filterQuery.setOperator(FilterQueryOperator.OR);
                }
                filterQueries.add(filterQuery);
            }
        });

        return filterQueries;
    }

    private void populateFieldsInResponse(final SearchSourceBuilder target, final List<EsIndexedProperty> indexedProperties) {
        if (CollectionUtils.isNotEmpty(indexedProperties)) {
            // Exclude fields from the response
            final Map<Boolean, List<EsIndexedProperty>> indexedPropertyMap = indexedProperties.stream()
                    .collect(Collectors.partitioningBy(property -> BooleanUtils.isTrue(property.isIncludeInResponse())));
            final List<EsIndexedProperty> includedFields = indexedPropertyMap.get(Boolean.TRUE);
            final List<EsIndexedProperty> excludedFields = indexedPropertyMap.get(Boolean.FALSE);
            target.fetchSource(CollectionUtils.isNotEmpty(includedFields)
                    ? includedFields.stream().map(property -> fieldNameResolver.resolve(property)).toArray(String[]::new)
                    : null, CollectionUtils.isNotEmpty(excludedFields)
                    ? excludedFields.stream().map(property -> fieldNameResolver.resolve(property)).toArray(String[]::new) : null);
        }
    }
}
