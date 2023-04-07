package digi.ecomm.searchstandardapi.controller;

import digi.ecomm.commercesearch.exception.NoValidElasticsearchConfigException;
import digi.ecomm.datatransferobject.search.response.ProductSearchResponse;
import digi.ecomm.datatransferobject.search.response.ProductSuggestionResponse;
import digi.ecomm.searchstandardapi.facade.ProductSearchFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/products")
public class ProductSearchController {

    private static final String DEFAULT_PAGE_SIZE = "20";
    private static final String DEFAULT_CURRENT_PAGE = "0";

    @Resource
    private ProductSearchFacade searchFacade;

    /**
     * @param query       Serialized query, free text search, facets.
     *                    The format of a serialized query: freeTextSearch:sort:facetKey1:facetValue1:facetKey2:facetValue2
     * @param currentPage The current result page requested
     * @param pageSize    The number of results returned per page
     * @param sort        Sorting method applied to the return results
     * @return
     */
    @GetMapping("/search")
    public ProductSearchResponse getProducts(@RequestParam(required = false) final String query,
                                             @RequestParam(defaultValue = DEFAULT_CURRENT_PAGE) final int currentPage,
                                             @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) final int pageSize,
                                             @RequestParam(required = false) final String sort)
            throws IOException, NoValidElasticsearchConfigException {

        return searchFacade.searchProducts(query, currentPage, pageSize, sort);
    }

    /**
     * @param query    Serialized query, prefix query
     * @param pageSize The number of results returned
     * @return
     */
    @GetMapping("/suggest")
    public ProductSuggestionResponse getProducts(@RequestParam(required = false) final String query,
                                                 @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) final int pageSize)
            throws IOException, NoValidElasticsearchConfigException {

        return searchFacade.getProductSuggestions(query, pageSize);
    }
}
