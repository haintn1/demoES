package digi.ecomm.searchstandardapi.facade.impl;

import digi.ecomm.commercesearch.client.EsContext;
import digi.ecomm.commercesearch.client.EsContextFactory;
import digi.ecomm.commercesearch.exception.NoValidElasticsearchConfigException;
import digi.ecomm.commercesearch.search.data.PageableData;
import digi.ecomm.commercesearch.search.data.SearchQueryData;
import digi.ecomm.commercesearch.search.data.SearchResponseWrapper;
import digi.ecomm.commercesearch.search.data.SuggestionQueryData;
import digi.ecomm.commercesearch.search.service.SearchService;
import digi.ecomm.commercesearch.strategy.EsFacetSearchConfigSelectionStrategy;
import digi.ecomm.datatransferobject.search.response.ProductSearchResponse;
import digi.ecomm.datatransferobject.search.response.ProductSuggestionResponse;
import digi.ecomm.entity.commercesearch.EsIndexedEntityType;
import digi.ecomm.platformservice.converter.Converter;
import digi.ecomm.searchstandardapi.facade.ProductSearchFacade;
import digi.ecomm.searchstandardapi.facade.SearchQueryCodec;
import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;

public class ProductSearchFacadeImpl implements ProductSearchFacade {

    private final EsFacetSearchConfigSelectionStrategy facetSearchConfigSelectionStrategy;
    private final SearchQueryCodec<SearchQueryData> searchQueryCodec;
    private final SearchService searchService;
    private final EsContextFactory contextFactory;
    private final Converter<SearchResponse, ProductSuggestionResponse> productSuggestionResponseConverter;
    private final Converter<SearchResponseWrapper, ProductSearchResponse> productSearchResponseConverter;

    public ProductSearchFacadeImpl(final EsFacetSearchConfigSelectionStrategy facetSearchConfigSelectionStrategy,
                                   final SearchQueryCodec<SearchQueryData> searchQueryCodec,
                                   final SearchService searchService, final EsContextFactory contextFactory,
                                   final Converter<SearchResponse, ProductSuggestionResponse> productSuggestionResponseConverter,
                                   final Converter<SearchResponseWrapper, ProductSearchResponse> productSearchResponseConverter) {
        this.facetSearchConfigSelectionStrategy = facetSearchConfigSelectionStrategy;
        this.searchQueryCodec = searchQueryCodec;
        this.searchService = searchService;
        this.contextFactory = contextFactory;
        this.productSuggestionResponseConverter = productSuggestionResponseConverter;
        this.productSearchResponseConverter = productSearchResponseConverter;
    }

    @Override
    public ProductSearchResponse searchProducts(final String query, final int currentPage, final int pageSize,
                                                final String sort) throws NoValidElasticsearchConfigException, IOException {
        final EsContext context =
                contextFactory.get(facetSearchConfigSelectionStrategy.getCurrentEsFacetSearchConfig(), EsIndexedEntityType.PRODUCT);
        final SearchQueryData searchQueryData = searchQueryCodec.decodeQuery(query);
        final PageableData pageableData = createPageableData(currentPage, pageSize, sort);
        final SearchResponse searchResponse = searchService.search(context, searchQueryData, pageableData);
        final ProductSearchResponse response =
                productSearchResponseConverter.convert(createSearchResponseWrapper(searchResponse, context));
        populatePagination(response, currentPage, pageSize);
        return response;
    }

    private SearchResponseWrapper createSearchResponseWrapper(final SearchResponse searchResponse, final EsContext context) {
        final SearchResponseWrapper responseWrapper = new SearchResponseWrapper();
        responseWrapper.setResponse(searchResponse);
        responseWrapper.setContext(context);
        return responseWrapper;
    }

    private void populatePagination(final ProductSearchResponse response, final int currentPage, final int pageSize) {
        response.getPage().setCurrentPage(currentPage);
        response.getPage().setPageSize(pageSize);
        response.getPage().setTotalPages((int) (Math.ceil((1.0d * response.getPage().getTotalElements()) / pageSize)));
    }

    private PageableData createPageableData(final int currentPage, final int pageSize, final String sort) {
        final PageableData pageable = new PageableData();
        pageable.setCurrentPage(currentPage);
        pageable.setPageSize(pageSize);
        pageable.setSort(sort);
        return pageable;
    }

    @Override
    public ProductSuggestionResponse getProductSuggestions(final String query, final int pageSize)
            throws NoValidElasticsearchConfigException, IOException {
        final EsContext context =
                contextFactory.get(facetSearchConfigSelectionStrategy.getCurrentEsFacetSearchConfig(), EsIndexedEntityType.PRODUCT);
        final SuggestionQueryData suggestionQueryData = createSuggestionQueryData(context, query, pageSize);
        final SearchResponse searchResponse = searchService.suggest(context, suggestionQueryData);
        return productSuggestionResponseConverter.convert(searchResponse);
    }

    private SuggestionQueryData createSuggestionQueryData(final EsContext context, final String query, final int pageSize) {
        final SuggestionQueryData suggestionQueryData = new SuggestionQueryData();
        suggestionQueryData.setText(query);
        suggestionQueryData.setPageSize(pageSize);
        suggestionQueryData.setContext(context);
        return suggestionQueryData;
    }
}
