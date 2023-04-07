package digi.ecomm.commercesearch.strategy;

import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import org.elasticsearch.search.aggregations.AggregationBuilder;


public interface EsAggregationBuilderFactory {

    /**
     * Create the corresponding {@link AggregationBuilder}.
     *
     * @param property
     * @return {@link AggregationBuilder} or null if cannot resolve
     * @throws IllegalArgumentException if cannot resolve the corresponding aggregation type
     */
    AggregationBuilder get(EsIndexedProperty property);
}
