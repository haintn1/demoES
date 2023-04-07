package digi.ecomm.commercesearch.search.populator;

import digi.ecomm.commercesearch.search.data.SuggestionQueryData;
import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Converter;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.util.ServicesUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class SuggestionSearchRequestPopulator implements Populator<SuggestionQueryData, SearchRequest> {

    private final Converter<SuggestionQueryData, SearchSourceBuilder> suggestionSearchSourceBuilderConverter;

    public SuggestionSearchRequestPopulator(
            final Converter<SuggestionQueryData, SearchSourceBuilder> suggestionSearchSourceBuilderConverter) {
        this.suggestionSearchSourceBuilderConverter = suggestionSearchSourceBuilderConverter;
    }


    @Override
    public void populate(final SuggestionQueryData source, final SearchRequest target) throws ConversionException {
        ServicesUtils.validateParameterNotNullStandardMessage("source", source);

        target.indices(source.getContext().getIndex().getName());
        target.source(suggestionSearchSourceBuilderConverter.convert(source));
    }
}
