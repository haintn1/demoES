package digi.ecomm.searchstandardapi.populator;

import digi.ecomm.datatransferobject.search.response.ProductSuggestionResponse;
import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.suggest.Suggest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSuggestionResponsePopulator implements Populator<SearchResponse, ProductSuggestionResponse> {

    @Override
    public void populate(final SearchResponse source, final ProductSuggestionResponse target) throws ConversionException {
        ServicesUtils.validateParameterNotNullStandardMessage("source", source);

        final List<SuggestText> suggestTexts = new ArrayList<>();
        final Suggest suggest = source.getSuggest();
        final Iterator<Suggest.Suggestion<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>>> iterator
                = suggest.iterator();
        while (iterator.hasNext()) {
            suggestTexts.addAll(iterator.next().getEntries().stream()
                    .flatMap(entry -> entry.getOptions().stream())
                    .map(option -> new SuggestText(option.getText().string(), option.getScore()))
                    .collect(Collectors.toList()));
        }
        target.setResults(suggestTexts.stream().sorted().map(SuggestText::getText).collect(Collectors.toList()));
    }

    @AllArgsConstructor
    @Getter
    private static class SuggestText implements Comparable<SuggestText> {
        private final String text;
        private final float score;

        @Override
        public int compareTo(final SuggestText other) {
            return Float.compare(other.score, this.score);
        }

        @Override
        public boolean equals(final Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}
