package digi.ecomm.searchstandardapi.strategy;

import digi.ecomm.commercesearch.SearchProperties;
import digi.ecomm.commercesearch.exception.NoValidElasticsearchConfigException;
import digi.ecomm.commercesearch.strategy.EsFacetSearchConfigSelectionStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StaticProductFacetSearchConfigSelectionStrategyImpl implements EsFacetSearchConfigSelectionStrategy {

    private final SearchProperties searchProperties;

    @Override
    public String getCurrentEsFacetSearchConfig() throws NoValidElasticsearchConfigException {
        final String facetSearchConfigName = searchProperties.getProduct().getConfigName();
        if (facetSearchConfigName == null) {
            throw new NoValidElasticsearchConfigException("No valid EsFacetSearchConfig configured.");
        }
        return facetSearchConfigName;
    }
}
