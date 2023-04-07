package digi.ecomm.commercesearch.search.service.impl;

import digi.ecomm.commercesearch.client.EsClientFactory;
import digi.ecomm.commercesearch.client.EsContext;
import digi.ecomm.commercesearch.search.data.PageableData;
import digi.ecomm.commercesearch.search.data.SearchQueryData;
import digi.ecomm.commercesearch.search.data.SearchQueryPageableData;
import digi.ecomm.commercesearch.search.data.SuggestionQueryData;
import digi.ecomm.commercesearch.search.service.SearchService;
import digi.ecomm.platformservice.converter.Converter;
import digi.ecomm.platformservice.util.ServicesUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SearchServiceImpl implements SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

    private final EsClientFactory clientFactory;
    private final Converter<SearchQueryPageableData, SearchRequest> searchRequestConverter;
    private final Converter<SuggestionQueryData, SearchRequest> suggestionSearchRequestConverter;

    public SearchServiceImpl(final EsClientFactory clientFactory,
                             final Converter<SearchQueryPageableData, SearchRequest> searchRequestConverter,
                             final Converter<SuggestionQueryData, SearchRequest> suggestionSearchRequestConverter) {
        this.clientFactory = clientFactory;
        this.searchRequestConverter = searchRequestConverter;
        this.suggestionSearchRequestConverter = suggestionSearchRequestConverter;
    }

    @Override
    public SearchResponse search(final EsContext context, final SearchQueryData searchQueryData,
                                 final PageableData pageableData) throws IOException {
        ServicesUtils.validateParameterNotNullStandardMessage("context", context);
        ServicesUtils.validateParameterNotNullStandardMessage("searchQueryData", searchQueryData);
        ServicesUtils.validateParameterNotNullStandardMessage("pageableData", pageableData);

        final SearchQueryPageableData searchQueryPageableData = buildSearchQueryPageableData(context, searchQueryData, pageableData);
        final SearchRequest searchRequest = searchRequestConverter.convert(searchQueryPageableData);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Search request body {}", searchRequest.source());
        }

        final RestHighLevelClient client = clientFactory.get(context.getFacetSearchConfig());
        final SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Search response body {}", searchResponse);
        }
        return searchResponse;
    }

    private SearchQueryPageableData buildSearchQueryPageableData(final EsContext context,
                                                                 final SearchQueryData searchQueryData,
                                                                 final PageableData pageableData) {
        final SearchQueryPageableData searchQueryPageableData = new SearchQueryPageableData();
        searchQueryPageableData.setContext(context);
        searchQueryPageableData.setSearchQueryData(searchQueryData);
        searchQueryPageableData.setPageableData(pageableData);
        return searchQueryPageableData;
    }

    @Override
    public SearchResponse suggest(final EsContext context, final SuggestionQueryData suggestionQueryData) throws IOException {
        ServicesUtils.validateParameterNotNullStandardMessage("context", context);
        ServicesUtils.validateParameterNotNullStandardMessage("suggestionQueryData", suggestionQueryData);

        suggestionQueryData.setContext(context);
        final SearchRequest searchRequest = suggestionSearchRequestConverter.convert(suggestionQueryData);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Suggest request body {}", searchRequest.source());
        }

        final RestHighLevelClient client = clientFactory.get(context.getFacetSearchConfig());
        final SearchResponse suggestionSearchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Suggest response body {}", suggestionSearchResponse);
        }
        return suggestionSearchResponse;
    }
}
