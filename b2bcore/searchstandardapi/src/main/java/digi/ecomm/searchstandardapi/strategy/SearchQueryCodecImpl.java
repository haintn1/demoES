package digi.ecomm.searchstandardapi.strategy;

import digi.ecomm.commercesearch.search.data.SearchQueryData;
import digi.ecomm.commercesearch.search.data.SearchQueryTermData;
import digi.ecomm.searchstandardapi.facade.SearchQueryCodec;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchQueryCodecImpl implements SearchQueryCodec<SearchQueryData> {

    private static final String COLON = ":";

    @Override
    public SearchQueryData decodeQuery(final String query) {
        final SearchQueryData searchQuery = new SearchQueryData();

        if (query == null) {
            return searchQuery;
        }

        final String[] parts = query.split(COLON);

        if (parts.length > 0) {
            searchQuery.setFreeTextSearch(parts[0]);
            if (parts.length > 1) {
                searchQuery.setSort(parts[1]);
            }
        }

        final List<SearchQueryTermData> filters = new ArrayList<>();
        for (int i = 2; i < parts.length; i = i + 2) {
            final SearchQueryTermData term = new SearchQueryTermData();
            term.setKey(parts[i]);
            term.setValue(parts[i + 1]);
            filters.add(term);
        }
        searchQuery.setFilterTerms(filters);

        return searchQuery;
    }

    @Override
    public String encodeQuery(final SearchQueryData searchQueryData) {
        if (searchQueryData == null) {
            return null;
        }

        final StringBuilder builder = new StringBuilder();
        builder.append(StringUtils.defaultString(searchQueryData.getFreeTextSearch()));

        if (searchQueryData.getSort() != null || CollectionUtils.isNotEmpty(searchQueryData.getFilterTerms())) {
            builder.append(COLON);
            builder.append(StringUtils.defaultString(searchQueryData.getSort()));
        }

        final List<SearchQueryTermData> terms = searchQueryData.getFilterTerms();
        if (CollectionUtils.isNotEmpty(terms)) {
            for (final SearchQueryTermData term : searchQueryData.getFilterTerms()) {
                builder.append(COLON);
                builder.append(term.getKey());
                builder.append(COLON);
                builder.append(term.getValue());
            }
        }

        return builder.toString();
    }
}
