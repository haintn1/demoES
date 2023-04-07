package digi.ecomm.commercesearch.strategy;

import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.entity.commercesearch.advance.EsBoostRule;
import org.elasticsearch.index.query.QueryBuilder;

public interface EsBoostRuleFilterBuilderFactory {

    /**
     * Create the corresponding {@link QueryBuilder}.
     *
     * @param property
     * @param boostRule
     * @return {@link QueryBuilder} or null if cannot resolve
     * @throws IllegalArgumentException if cannot resolve the corresponding query type
     */
    QueryBuilder get(EsIndexedProperty property, EsBoostRule boostRule);
}
