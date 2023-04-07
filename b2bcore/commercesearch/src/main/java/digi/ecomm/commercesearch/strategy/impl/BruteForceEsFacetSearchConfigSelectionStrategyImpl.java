package digi.ecomm.commercesearch.strategy.impl;

import digi.ecomm.commercesearch.exception.NoValidElasticsearchConfigException;
import digi.ecomm.commercesearch.strategy.EsFacetSearchConfigSelectionStrategy;

public class BruteForceEsFacetSearchConfigSelectionStrategyImpl implements EsFacetSearchConfigSelectionStrategy {

    @Override
    public String getCurrentEsFacetSearchConfig() throws NoValidElasticsearchConfigException {
        throw new NoValidElasticsearchConfigException("No valid EsFacetSearchConfig configured.");
    }
}
