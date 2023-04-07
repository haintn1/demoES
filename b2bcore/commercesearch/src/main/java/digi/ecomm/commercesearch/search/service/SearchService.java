package digi.ecomm.commercesearch.search.service;

import digi.ecomm.commercesearch.client.EsContext;
import digi.ecomm.commercesearch.search.data.PageableData;
import digi.ecomm.commercesearch.search.data.SearchQueryData;
import digi.ecomm.commercesearch.search.data.SuggestionQueryData;
import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;

public interface SearchService {
    /**
     * Initiate a search.
     */
    SearchResponse search(EsContext context, SearchQueryData searchQueryData, PageableData pageableData) throws IOException;

    /**
     * Initiate a suggestion.
     */
    SearchResponse suggest(EsContext context, SuggestionQueryData suggestionQueryData) throws IOException;
}
