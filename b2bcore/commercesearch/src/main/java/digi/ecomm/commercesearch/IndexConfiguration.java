package digi.ecomm.commercesearch;

import digi.ecomm.commercesearch.client.EsClientFactory;
import digi.ecomm.commercesearch.client.EsContextFactory;
import digi.ecomm.commercesearch.index.provider.data.IndexedProduct;
import digi.ecomm.commercesearch.index.provider.impl.AbstractFieldValueProvider;
import digi.ecomm.commercesearch.index.provider.impl.UpperCaseProductNameValueProviderImpl;
import digi.ecomm.commercesearch.index.service.IndexService;
import digi.ecomm.commercesearch.index.service.impl.IndexServiceImpl;
import digi.ecomm.commercesearch.provider.IndexedSourceProvider;
import digi.ecomm.commercesearch.repository.EsStopWordRepository;
import digi.ecomm.commercesearch.repository.EsSynonymConfigRepository;
import digi.ecomm.commercesearch.strategy.EsFacetSearchConfigSelectionStrategy;
import digi.ecomm.commercesearch.strategy.EsIndexedPropertyGroupStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IndexConfiguration {

    @Bean({"defaultIndexService", "indexService"})
    public IndexService indexService(
            final EsClientFactory clientFactory,
            final EsContextFactory contextFactory,
            final EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy,
            final EsStopWordRepository stopWordRepository,
            final EsSynonymConfigRepository synonymConfigRepository,
            final EsFacetSearchConfigSelectionStrategy facetSearchConfigSelectionStrategy,
            final IndexedSourceProvider<IndexedProduct> sourceProvider) {
        return new IndexServiceImpl(
                clientFactory, indexedPropertyGroupStrategy,
                stopWordRepository, synonymConfigRepository,
                contextFactory, facetSearchConfigSelectionStrategy,
                sourceProvider
        );
    }

    @Bean("upperCaseProductNameValueProvider")
    public AbstractFieldValueProvider upperCaseProductNameValueProvider() {
        return new UpperCaseProductNameValueProviderImpl();
    }
}
