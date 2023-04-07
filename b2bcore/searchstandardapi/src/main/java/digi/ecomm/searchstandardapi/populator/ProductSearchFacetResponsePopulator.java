package digi.ecomm.searchstandardapi.populator;

import digi.ecomm.commercesearch.search.data.SearchResponseWrapper;
import digi.ecomm.datatransferobject.search.response.ProductSearchResponse;
import digi.ecomm.datatransferobject.search.response.SearchFacet;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.bucket.nested.Nested;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import java.util.*;
import java.util.stream.Collectors;

public class ProductSearchFacetResponsePopulator implements Populator<SearchResponseWrapper, ProductSearchResponse> {
    private static final String DOT = ".";
    private static final String DASH = "-";

    @Override
    public void populate(final SearchResponseWrapper source, final ProductSearchResponse target) throws ConversionException {
        ServicesUtils.validateParameterNotNullStandardMessage("source", source);
        ServicesUtils.validateParameterNotNullStandardMessage("response", source.getResponse());
        ServicesUtils.validateParameterNotNullStandardMessage("context", source.getContext());

        final SearchResponse response = source.getResponse();
        final List<SearchFacet> searchFacets = new ArrayList<>();
        final Map<String, FacetProperty> facetPropertyMapping = createFacetPropertyMapping(source.getContext().getIndexedProperties());

        response.getAggregations().asMap().forEach((fieldName, aggregation) -> {
            if (aggregation instanceof Nested) {
                final Map<String, Aggregation> nestedAggregationMap = ((Nested) aggregation).getAggregations().asMap();
                nestedAggregationMap.forEach((nestedFieldName, nestedAggregation) ->
                        searchFacets.add(getSearchFacet(nestedFieldName, nestedAggregation, facetPropertyMapping))
                );
            } else {
                searchFacets.add(getSearchFacet(fieldName, aggregation, facetPropertyMapping));
            }
        });
        searchFacets.removeIf(Objects::isNull);
        Collections.sort(searchFacets, (o1, o2) -> o2.getPriority() - o1.getPriority());
        target.setFacets(searchFacets);
    }

    private Map<String, FacetProperty> createFacetPropertyMapping(final List<EsIndexedProperty> indexedProperties) {
        final Map<String, FacetProperty> facetPropertyMapping = new HashMap<>();
        indexedProperties.forEach(property -> {
            facetPropertyMapping.put(property.getName(), new FacetProperty(property.getDisplayName(),
                    BooleanUtils.isTrue(property.getIncludeInFacets()), property.getPriority()));
            if (property.getMultiFieldsType() != null) {
                facetPropertyMapping.put(StringUtils.join(property.getName(), DOT, property.getMultiFieldsType().getValue()),
                        new FacetProperty(property.getDisplayName(), BooleanUtils.isTrue(property.getIncludeInFacets()),
                                property.getPriority()));
            }
        });
        return facetPropertyMapping;
    }

    private SearchFacet getSearchFacet(final String fieldName, final Aggregation aggregation,
                                       final Map<String, FacetProperty> facetPropertyMapping) {

        final FacetProperty facetProperty = facetPropertyMapping.get(fieldName);
        final SearchFacet searchFacet = new SearchFacet();
        searchFacet.setName(fieldName);
        searchFacet.setDisplayName(Optional.ofNullable(facetProperty).map(FacetProperty::getName).orElse(null));
        searchFacet.setPriority(Optional.ofNullable(facetProperty).map(FacetProperty::getPriority).orElse(0));

        if (aggregation instanceof Terms) {
            final List<? extends Terms.Bucket> buckets = ((Terms) aggregation).getBuckets();
            searchFacet.setValues(buckets.stream().map(bucket -> {
                if (Optional.ofNullable(facetProperty).map(FacetProperty::isIncludeInFacets).orElse(false)
                        && bucket.getDocCount() > 0) {
                    return new SearchFacet.Value(bucket.getKeyAsString(), bucket.getKeyAsString(), bucket.getDocCount());
                } else {
                    return null;
                }
            }).collect(Collectors.toList()));

        } else if (aggregation instanceof Range) {
            final List<? extends Range.Bucket> buckets = ((Range) aggregation).getBuckets();
            searchFacet.setValues(buckets.stream().map(bucket -> {
                if (Optional.ofNullable(facetProperty).map(FacetProperty::isIncludeInFacets).orElse(false)
                        && bucket.getDocCount() > 0) {
                    return new SearchFacet.RangeValue(bucket.getKeyAsString(), StringUtils.join(bucket.getFromAsString(),
                            DASH, bucket.getToAsString()), bucket.getFromAsString(), bucket.getToAsString(),
                            bucket.getDocCount());
                } else {
                    return null;
                }
            }).collect(Collectors.toList()));

        } else {
            throw new IllegalArgumentException(String.format("Unsupported aggregation type [%s] for field [%s]",
                    aggregation.getClass().getName(), fieldName));
        }

        searchFacet.getValues().removeIf(Objects::isNull);
        return CollectionUtils.isNotEmpty(searchFacet.getValues()) ? searchFacet : null;
    }

    @RequiredArgsConstructor
    @Getter
    @Setter
    private class FacetProperty {
        private final String name;
        private final boolean includeInFacets;
        private final int priority;
    }
}
