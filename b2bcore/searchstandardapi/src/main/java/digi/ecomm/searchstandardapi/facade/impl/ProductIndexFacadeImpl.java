package digi.ecomm.searchstandardapi.facade.impl;

import digi.ecomm.commercesearch.client.EsContextFactory;
import digi.ecomm.commercesearch.exception.NoValidElasticsearchConfigException;
import digi.ecomm.commercesearch.index.service.IndexService;
import digi.ecomm.commercesearch.strategy.EsFacetSearchConfigSelectionStrategy;
import digi.ecomm.datatransferobject.search.response.ProductDeleteResponse;
import digi.ecomm.datatransferobject.search.response.ProductFullIndexResponse;
import digi.ecomm.datatransferobject.search.response.ProductUpdateResponse;
import digi.ecomm.searchstandardapi.facade.ProductIndexFacade;

import java.io.IOException;

public class ProductIndexFacadeImpl implements ProductIndexFacade {

    private final IndexService indexService;
    private final EsContextFactory contextFactory;
    private final EsFacetSearchConfigSelectionStrategy facetSearchConfigSelectionStrategy;

    public ProductIndexFacadeImpl(final IndexService indexService,
                                  final EsContextFactory contextFactory,
                                  final EsFacetSearchConfigSelectionStrategy facetSearchConfigSelectionStrategy) {
        this.indexService = indexService;
        this.contextFactory = contextFactory;
        this.facetSearchConfigSelectionStrategy = facetSearchConfigSelectionStrategy;
    }

    @Override
    public ProductFullIndexResponse fullIndexing() throws IOException, NoValidElasticsearchConfigException {
        indexService.fullIndexing();

        return new ProductFullIndexResponse();
    }

    @Override
    public ProductUpdateResponse updateDocument(final Long productId) throws IOException, NoValidElasticsearchConfigException {
        indexService.updateDocument(productId);

        return new ProductUpdateResponse();
    }

    @Override
    public ProductDeleteResponse deleteDocument(final Long productId) throws IOException, NoValidElasticsearchConfigException {
        indexService.deleteDocument(productId);

        return new ProductDeleteResponse();
    }
}
