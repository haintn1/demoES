package digi.ecomm.commercesearch.client;

import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import org.elasticsearch.client.RestHighLevelClient;

public interface EsClientFactory {

    /**
     * Create Elasticsearch client.
     *
     * @param facetSearchConfig
     * @return {@link RestHighLevelClient} or throw {@link IllegalArgumentException} if {@code facetSearchConfig} is null
     */
    RestHighLevelClient get(EsFacetSearchConfig facetSearchConfig);
}
