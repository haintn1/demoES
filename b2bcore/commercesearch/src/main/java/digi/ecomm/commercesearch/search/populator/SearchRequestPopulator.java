package digi.ecomm.commercesearch.search.populator;

import digi.ecomm.commercesearch.search.data.SearchQueryPageableData;
import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Converter;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.util.ServicesUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class SearchRequestPopulator implements Populator<SearchQueryPageableData, SearchRequest> {

    private final Converter<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderConverter;

    public SearchRequestPopulator(final Converter<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderConverter) {
        this.searchSourceBuilderConverter = searchSourceBuilderConverter;
    }

    @Override
    public void populate(final SearchQueryPageableData source, final SearchRequest target) throws ConversionException {
        ServicesUtils.validateParameterNotNullStandardMessage("source", source);

        target.indices(source.getContext().getIndex().getName());
        target.source(searchSourceBuilderConverter.convert(source));
    }
}
