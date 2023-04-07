package digi.ecomm.searchstandardapi.facade;

import digi.ecomm.commercesearch.exception.NoValidElasticsearchConfigException;
import digi.ecomm.datatransferobject.search.response.ProductSearchResponse;
import digi.ecomm.datatransferobject.search.response.ProductSuggestionResponse;

import java.io.IOException;

public interface ProductSearchFacade {

    /**
     * Search products.
     *
     * @param query
     * @param currentPage
     * @param pageSize
     * @param sort
     * @return
     * @throws NoValidElasticsearchConfigException
     * @throws IOException
     */
    ProductSearchResponse searchProducts(String query, int currentPage, int pageSize, String sort)
            throws NoValidElasticsearchConfigException, IOException;

    /**
     * Get suggestions.
     *
     * @param query
     * @param pageSize
     * @return
     * @throws NoValidElasticsearchConfigException
     * @throws IOException
     */
    ProductSuggestionResponse getProductSuggestions(String query, int pageSize)
            throws NoValidElasticsearchConfigException, IOException;
}
