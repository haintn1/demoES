package digi.ecomm.commercesearch.search.populator;

import digi.ecomm.commercesearch.search.data.PageableData;
import digi.ecomm.commercesearch.search.data.SearchQueryPageableData;
import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.util.ServicesUtils;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.Objects;

public class SearchSourceBuilderPagingPopulator implements Populator<SearchQueryPageableData, SearchSourceBuilder> {

    private static final int ZERO = 0;

    @Override
    public void populate(final SearchQueryPageableData source, final SearchSourceBuilder target) throws ConversionException {
        ServicesUtils.validateParameterNotNullStandardMessage("source", source);

        final PageableData pageableData = source.getPageableData();
        if (pageableData != null) {
            int pageSize = pageableData.getPageSize();
            if (pageSize <= ZERO) {
                final Integer defaultPageSize = source.getContext().getFacetSearchConfig().getSearchConfig().getPageSize();
                if (Objects.nonNull(defaultPageSize) && defaultPageSize > ZERO) {
                    pageSize = defaultPageSize;
                }
            }
            target.from(pageableData.getCurrentPage() * pageSize);
            target.size(pageSize);
        }
    }
}
