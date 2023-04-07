package digi.ecomm.searchstandardapi.facade;

import digi.ecomm.commercesearch.exception.NoValidElasticsearchConfigException;
import digi.ecomm.datatransferobject.search.response.ProductDeleteResponse;
import digi.ecomm.datatransferobject.search.response.ProductFullIndexResponse;
import digi.ecomm.datatransferobject.search.response.ProductUpdateResponse;

import java.io.IOException;

public interface ProductIndexFacade {
    /**
     * Perform indexing products.
     *
     * @return
     */
    ProductFullIndexResponse fullIndexing() throws IOException, NoValidElasticsearchConfigException;

    /**
     * Perform updating a product.
     *
     * @return
     */
    ProductUpdateResponse updateDocument(Long productId) throws IOException, NoValidElasticsearchConfigException;

    /**
     * Perform deleting a product.
     *
     * @return
     */
    ProductDeleteResponse deleteDocument(Long productId) throws IOException, NoValidElasticsearchConfigException;
}
