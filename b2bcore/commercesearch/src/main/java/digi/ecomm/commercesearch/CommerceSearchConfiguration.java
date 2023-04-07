package digi.ecomm.commercesearch;

import digi.ecomm.commercesearch.client.EsClientFactory;
import digi.ecomm.commercesearch.client.EsContextFactory;
import digi.ecomm.commercesearch.client.impl.EsClientFactoryImpl;
import digi.ecomm.commercesearch.client.impl.EsContextFactoryImpl;
import digi.ecomm.commercesearch.repository.*;
import digi.ecomm.commercesearch.strategy.EsIndexedPropertyGroupStrategy;
import digi.ecomm.commercesearch.strategy.impl.EsIndexedPropertyGroupStrategyImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommerceSearchConfiguration {

    @Bean({"defaultRestTemplate", "restTemplate"})
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean("esClientFactory")
    public EsClientFactory esClientFactory(final EsServerRepository serverRepository) {
        return new EsClientFactoryImpl(serverRepository);
    }

    @Bean("esContextFactory")
    public EsContextFactory esContextFactory(
            final EsFacetSearchConfigRepository facetSearchConfigRepository,
            final EsIndexRepository indexRepository,
            final EsIndexedPropertyRepository indexedPropertyRepository,
            final EsAdvancedSearchConfigRepository advancedSearchConfigRepository) {
        return new EsContextFactoryImpl(facetSearchConfigRepository, indexRepository, indexedPropertyRepository,
                advancedSearchConfigRepository);
    }

    @Bean("indexedPropertyGroupStrategy")
    public EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy() {
        return new EsIndexedPropertyGroupStrategyImpl();
    }

    @Bean("searchSampleData")
    public SearchSampleData searchSampleData() {
        return new SearchSampleData();
    }
}
