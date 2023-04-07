package digi.ecomm.elasticpathsdk.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Query {
    private List<String> includes;
    private Pageable pageable;

    private Query() {
    }

    /**
     * Create builder.
     *
     * @return
     */
    public static QueryBuilder builder() {
        return new QueryBuilder();
    }

    /**
     * Convert to query string.
     *
     * @return
     */
    public String toQueryString() {
        final StringBuilder builder = new StringBuilder();
        if (this.pageable != null) {
            if (this.pageable.getCurrentPage() != null) {
                builder.append("page[current]=").append(this.pageable.getCurrentPage());
            }
            if (this.pageable.getPageSize() != null) {
                if (builder.length() > 0) {
                    builder.append("&");
                }
                builder.append("page[limit]=").append(this.pageable.getPageSize());
            }
        }

        if (this.includes != null && !this.includes.isEmpty()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append("include=");
            this.includes.forEach(includedField -> builder.append(String.join(",", includedField)));
        }
        return builder.toString();
    }

    public static final class QueryBuilder {
        private List<String> includes;
        private Pageable pageable;

        /**
         * Set included fields.
         *
         * @param includedFields
         * @return
         */
        public QueryBuilder include(final String... includedFields) {
            this.includes = Stream.of(includedFields).collect(Collectors.toList());
            return this;
        }

        /**
         * Set current page.
         *
         * @param currentPage
         * @return
         */
        public QueryBuilder currentPage(final Integer currentPage) {
            if (currentPage != null) {
                if (this.pageable == null) {
                    this.pageable = new Pageable();
                }
                this.pageable.currentPage = currentPage;
            }
            return this;
        }

        /**
         * Set page size.
         *
         * @param pageSize
         * @return
         */
        public QueryBuilder pageSize(final Integer pageSize) {
            if (pageSize != null) {
                if (this.pageable == null) {
                    this.pageable = new Pageable();
                }
                this.pageable.pageSize = pageSize;
            }
            return this;
        }

        /**
         * Build query.
         *
         * @return
         */
        public Query build() {
            final Query query = new Query();
            query.includes = this.includes;
            query.pageable = this.pageable;
            return query;
        }
    }

    public static final class Pageable {
        private Integer currentPage;
        private Integer pageSize = 25;

        private Pageable() {
        }

        public Integer getCurrentPage() {
            return currentPage;
        }

        public Integer getPageSize() {
            return pageSize;
        }
    }

    public List<String> getIncludes() {
        return includes;
    }

    public Pageable getPageable() {
        return pageable;
    }
}
