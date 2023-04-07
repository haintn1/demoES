package digi.ecomm.commercesearch.index.data;

import com.google.common.base.Preconditions;
import digi.ecomm.platformservice.util.ServicesUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class IndexAnalysis implements ToMap {
    private static final String ANALYSIS_KEY = "analysis";
    private static final String ANALYZER_KEY = "analyzer";
    private static final String FILTER_KEY = "filter";

    private IndexAnalyzer analyzer;

    private IndexAnalysis() {
    }

    /**
     * Create index analysis builder.
     *
     * @return
     */
    public static IndexAnalysisBuilder builder() {
        return new IndexAnalysisBuilder();
    }

    public static class IndexAnalysisBuilder {
        private IndexAnalyzer analyzer;

        /**
         * Set analyzer.
         *
         * @param indexAnalyzer
         * @return
         */
        public IndexAnalysisBuilder analyzer(final IndexAnalyzer indexAnalyzer) {
            ServicesUtils.validateParameterNotNullStandardMessage("indexAnalyzer", indexAnalyzer);

            this.analyzer = indexAnalyzer;
            return this;
        }

        /**
         * Build index analysis.
         *
         * @return
         */
        public IndexAnalysis build() {
            Preconditions.checkArgument(Objects.nonNull(this.analyzer), "Analyzer should not be blank.");

            final IndexAnalysis analysis = new IndexAnalysis();
            analysis.analyzer = this.analyzer;
            return analysis;
        }
    }

    @Override
    public Map<String, Object> toMap() {
        final Map<String, Object> analysisMap = new HashMap<>();

        final Map<String, Object> analyzerMap = new HashMap<>();
        analyzerMap.put(Analyzer.DEFAULT.getValue(), this.analyzer.toMap());
        analyzerMap.put(Analyzer.DEFAULT_SEARCH.getValue(), this.analyzer.toMap());
        analysisMap.put(ANALYZER_KEY, analyzerMap);

        final Map<String, Object> filterMap = new HashMap<>();
        this.analyzer.getFilters().forEach(filter -> filterMap.put(filter.getName(), filter.toMap().get(filter.getName())));

        analysisMap.put(FILTER_KEY, filterMap);
        return Collections.singletonMap(ANALYSIS_KEY, analysisMap);
    }
}
