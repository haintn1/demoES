package digi.ecomm.commercesearch.search.populator;

import digi.ecomm.commercesearch.data.IndexedPropertyGroup;
import digi.ecomm.commercesearch.search.data.SearchQueryPageableData;
import digi.ecomm.commercesearch.strategy.EsAggregationBuilderFactory;
import digi.ecomm.commercesearch.strategy.EsIndexedPropertyGroupStrategy;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.entity.commercesearch.EsPropertyType;
import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.util.ServicesUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.List;
import java.util.Objects;

public class SearchSourceBuilderFacetPopulator implements Populator<SearchQueryPageableData, SearchSourceBuilder> {

    private final EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy;
    private final EsAggregationBuilderFactory aggregationBuilderFactory;

    public SearchSourceBuilderFacetPopulator(
            final EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy,
            final EsAggregationBuilderFactory aggregationBuilderFactory) {
        this.indexedPropertyGroupStrategy = indexedPropertyGroupStrategy;
        this.aggregationBuilderFactory = aggregationBuilderFactory;
    }

    @Override
    public void populate(final SearchQueryPageableData source, final SearchSourceBuilder target) throws ConversionException {
        ServicesUtils.validateParameterNotNullStandardMessage("source", source);

        final List<EsIndexedProperty> indexedProperties = source.getContext().getIndexedProperties();
        final List<IndexedPropertyGroup> indexedPropertyGroup = indexedPropertyGroupStrategy.group(indexedProperties);
        if (CollectionUtils.isNotEmpty(indexedPropertyGroup)) {
            // Aggregation on the field itself
            indexedPropertyGroup.stream()
                    .map(IndexedPropertyGroup::getProperty)
                    .filter(property -> BooleanUtils.isTrue(property.isFacet()))
                    .filter(property -> !Objects.equals(property.getType(), EsPropertyType.NESTED))
                    .forEach(property -> target.aggregation(aggregationBuilderFactory.get(property)));

            // Aggregation on the nested field
            indexedPropertyGroup.stream()
                    .flatMap(nestedProperty -> nestedProperty.getNestedProperties().stream())
                    .filter(nestedProperty -> BooleanUtils.isTrue(nestedProperty.isFacet()))
                    .forEach(nestedProperty -> {
                        final NestedAggregationBuilder nestedAggregationBuilder =
                                AggregationBuilders.nested(nestedProperty.getOuterProperty().getName(),
                                        nestedProperty.getOuterProperty().getName());
                        nestedAggregationBuilder.subAggregation(aggregationBuilderFactory.get(nestedProperty));
                        target.aggregation(nestedAggregationBuilder);
                    });
        }
    }
}
