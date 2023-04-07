package digi.ecomm.commercesearch.strategy;

import digi.ecomm.commercesearch.exception.NoValidElasticsearchConfigException;

public interface EsFacetSearchConfigSelectionStrategy {

    /**
     * Resolves suitable <code>EsFacetSearchConfig</code> that should be used for searching.
     *
     * @return facet search config name
     * @throws <code>NoValidElasticsearchConfigException</code>
     */
    String getCurrentEsFacetSearchConfig() throws NoValidElasticsearchConfigException;
}
