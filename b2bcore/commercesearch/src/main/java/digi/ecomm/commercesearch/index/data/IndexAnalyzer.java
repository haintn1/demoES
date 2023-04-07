package digi.ecomm.commercesearch.index.data;

import com.google.common.base.Preconditions;
import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public final class IndexAnalyzer implements ToMap {
    private static final String TOKENIZER_KEY = "tokenizer";
    private static final String FILTER_KEY = "filter";
    private static final String UNDERSCORE = "_";

    private static final List<TokenFilter> DEFAULT_BUILT_IN_TOKEN_FILTERS = Arrays.asList(TokenFilter.LOWERCASE);

    private String tokenizer = Tokenizer.STANDARD.getValue();
    private List<IndexFilter> filters;

    private IndexAnalyzer() {
    }

    /**
     * Create index analyzer builder.
     *
     * @return
     */
    public static IndexAnalyzerBuilder builder() {
        return new IndexAnalyzerBuilder();
    }

    public static final class IndexAnalyzerBuilder {
        private List<IndexFilter> filters;

        /**
         * Set filter.
         *
         * @param indexFilter
         * @return
         */
        public IndexAnalyzerBuilder filter(final IndexFilter indexFilter) {
            ServicesUtils.validateParameterNotNullStandardMessage("indexFilter", indexFilter);

            if (Objects.isNull(filters)) {
                filters = new ArrayList<>();
            }
            filters.add(indexFilter);
            return this;
        }

        /**
         * Build index analyzer.
         *
         * @return
         */
        public IndexAnalyzer build() {
            Preconditions.checkArgument(CollectionUtils.isNotEmpty(filters), "Filters should not be empty.");

            final IndexAnalyzer analyzer = new IndexAnalyzer();
            analyzer.filters = this.filters;
            return analyzer;
        }
    }

    @Override
    public Map<String, Object> toMap() {
        final Map<String, Object> analyzerMap = new HashMap<>();
        analyzerMap.put(TOKENIZER_KEY, this.tokenizer);
        final List<String> filterNames = this.filters.stream().map(IndexFilter::getName).collect(Collectors.toList());
        filterNames.addAll(DEFAULT_BUILT_IN_TOKEN_FILTERS.stream().map(TokenFilter::getValue).collect(Collectors.toList()));
        analyzerMap.put(FILTER_KEY, filterNames);
        return analyzerMap;
    }
}
