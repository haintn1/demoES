package digi.ecomm.commercesearch.strategy;

import digi.ecomm.commercesearch.search.data.SearchFilterQueryData;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import org.elasticsearch.index.query.QueryBuilder;

public interface EsFilterQueryBuilderFactory {

    /**
     * Create the corresponding {@link QueryBuilder}.
     *
     * @param property
     * @param searchFilterQueryData
     * @return {@link QueryBuilder} or null if cannot resolve
     * @throws IllegalArgumentException if cannot resolve the corresponding query type
     */
    QueryBuilder get(EsIndexedProperty property, SearchFilterQueryData searchFilterQueryData);
}
