package digi.ecomm.platformservice.rest.springdata.hal;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.hateoas.PagedModel;
import org.springframework.lang.Nullable;

import java.util.Collection;

public class SimplePagedModel<T> extends PagedModel<T> {

    public SimplePagedModel() {
    }

    public SimplePagedModel(final Collection<T> content, final PageMetadata metadata) {
        super(content, metadata);
    }

    /**
     * Create <code>SimplePagedModel</code>.
     *
     * @param content
     * @param metadata
     * @param <T>
     * @return
     */
    public static <T> SimplePagedModel<T> of(final Collection<T> content, @Nullable final PageMetadata metadata) {
        return new SimplePagedModel<>(content, metadata);
    }

    public static class PageMetadata extends PagedModel.PageMetadata {

        public PageMetadata() {
        }

        public PageMetadata(final long size, final long number, final long totalElements, final long totalPages) {
            super(size, number, totalElements, totalPages);
        }

        @JsonGetter("pageSize")
        @Override
        public long getSize() {
            return super.getSize();
        }

        @JsonGetter("totalElements")
        @Override
        public long getTotalElements() {
            return super.getTotalElements();
        }

        @JsonGetter("totalPages")
        @Override
        public long getTotalPages() {
            return super.getTotalPages();
        }

        @JsonGetter("currentPage")
        @Override
        public long getNumber() {
            return super.getNumber();
        }
    }
}
