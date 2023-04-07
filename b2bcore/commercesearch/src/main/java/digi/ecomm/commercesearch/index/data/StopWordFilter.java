package digi.ecomm.commercesearch.index.data;

import com.google.common.base.Preconditions;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public final class StopWordFilter extends IndexFilter {
    private static final String STOP_WORDS = "stopwords";

    private List<String> words;

    private StopWordFilter() {
        this.setType(FilterType.STOP);
    }

    /**
     * Create stop word builder.
     *
     * @return
     */
    public static StopWordFilterBuilder builder() {
        return new StopWordFilterBuilder();
    }

    public static class StopWordFilterBuilder {
        private String configName;
        private List<String> words;

        /**
         * Set config name.
         *
         * @param configName
         * @return
         */
        public StopWordFilterBuilder configName(final String configName) {
            this.configName = configName;
            return this;
        }

        /**
         * Set list of stop words.
         *
         * @param stopWords
         * @return
         */
        public StopWordFilterBuilder stops(final List<String> stopWords) {
            Preconditions.checkArgument(CollectionUtils.isNotEmpty(stopWords), "Stop words should not be empty.");

            if (Objects.isNull(this.words)) {
                this.words = new ArrayList<>();
            }
            this.words.addAll(stopWords);
            return this;
        }

        /**
         * Build stop word filter.
         *
         * @return
         */
        public StopWordFilter build() {
            Preconditions.checkArgument(StringUtils.isNotBlank(this.configName), "Config name should not be blank.");
            Preconditions.checkArgument(CollectionUtils.isNotEmpty(this.words), "Stop words should not be empty.");

            final StopWordFilter filter = new StopWordFilter();
            filter.setName(String.join(UNDERSCORE, this.configName.toLowerCase(), filter.getType().getValue()));
            filter.words = this.words;
            return filter;
        }
    }

    @Override
    public Map<String, Object> toMap() {
        final Map<String, Object> map = new HashMap<>();
        map.put(TYPE_KEY, this.getType().getValue());
        map.put(STOP_WORDS, this.words);
        return Collections.singletonMap(this.getName(), map);
    }
}
