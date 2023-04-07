package digi.ecomm.commercesearch.client;

import digi.ecomm.commercesearch.exception.NoValidElasticsearchConfigException;
import digi.ecomm.entity.commercesearch.EsIndexedEntityType;

public interface EsContextFactory {

    /**
     * Create Elasticsearch context.
     *
     * @param facetSearchConfigName
     * @param indexedEntityType
     * @return {@link EsContext} or throw {@link IllegalArgumentException} if either {@code facetSearchConfigName} or
     * {@code indexedEntityType} is null, or throw {@link NoValidElasticsearchConfigException} if there is neither facet search config
     * nor index found
     */
    EsContext get(String facetSearchConfigName, EsIndexedEntityType indexedEntityType) throws NoValidElasticsearchConfigException;
}
