package digi.ecomm.commercesearch.index.data;

import com.google.common.base.Preconditions;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public final class SynonymFilter extends IndexFilter {
    private static final String SYNONYMS_KEY = "synonyms";

    private List<String> synonyms;

    private SynonymFilter() {
        this.setType(FilterType.SYNONYM);
        this.synonyms = new ArrayList<>();
    }

    /**
     * Create synonym builder.
     *
     * @return
     */
    public static SynonymFilterBuilder builder() {
        return new SynonymFilterBuilder();
    }

    public static class SynonymFilterBuilder {
        private String configName;
        private List<Synonym> synonyms;

        /**
         * Set config name.
         *
         * @param configName
         * @return
         */
        public SynonymFilterBuilder configName(final String configName) {
            this.configName = configName;
            return this;
        }

        /**
         * Set list of synonyms.
         *
         * @param synonyms
         * @return
         */
        public SynonymFilterBuilder synonyms(final List<Synonym> synonyms) {
            Preconditions.checkArgument(CollectionUtils.isNotEmpty(synonyms), "Synonyms should not be empty.");

            if (Objects.isNull(this.synonyms)) {
                this.synonyms = new ArrayList<>();
            }
            this.synonyms.addAll(synonyms);
            return this;
        }

        /**
         * Build synonym filter.
         *
         * @return
         */
        public SynonymFilter build() {
            Preconditions.checkArgument(StringUtils.isNotBlank(this.configName), "Config name should not be blank.");
            Preconditions.checkArgument(CollectionUtils.isNotEmpty(this.synonyms), "Stop words should not be empty.");

            final SynonymFilter filter = new SynonymFilter();
            filter.setName(String.join(UNDERSCORE, this.configName.toLowerCase(), filter.getType().getValue()));
            filter.synonyms = this.synonyms.stream()
                    .map(synonym -> StringUtils.join(synonym.getFrom(), " => ", synonym.getTo()))
                    .collect(Collectors.toList());
            return filter;
        }
    }

    @Override
    public Map<String, Object> toMap() {
        final Map<String, Object> map = new HashMap<>();
        map.put(TYPE_KEY, this.getType().getValue());
        map.put(SYNONYMS_KEY, this.synonyms);
        return Collections.singletonMap(this.getName(), map);
    }
}
