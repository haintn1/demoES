package digi.ecomm.commercesearch.search.populator;

import digi.ecomm.commercesearch.data.IndexedPropertyGroup;
import digi.ecomm.commercesearch.repository.EsIndexedPropertyRepository;
import digi.ecomm.commercesearch.search.data.SuggestionQueryData;
import digi.ecomm.commercesearch.strategy.EsIndexedPropertyGroupStrategy;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.entity.commercesearch.EsPropertyType;
import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.util.ServicesUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;

import java.util.List;
import java.util.Objects;

public class SuggestionSearchSourceBuilderSuggestionPopulator implements Populator<SuggestionQueryData, SearchSourceBuilder> {

    private static final int ZERO = 0;
    private static final String DOT = ".";

    private final EsIndexedPropertyRepository indexedPropertyRepository;
    private final EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy;

    public SuggestionSearchSourceBuilderSuggestionPopulator(final EsIndexedPropertyRepository indexedPropertyRepository,
                                                            final EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy) {
        this.indexedPropertyRepository = indexedPropertyRepository;
        this.indexedPropertyGroupStrategy = indexedPropertyGroupStrategy;
    }

    @Override
    public void populate(final SuggestionQueryData source, final SearchSourceBuilder target) throws ConversionException {
        ServicesUtils.validateParameterNotNullStandardMessage("source", source);

        final List<EsIndexedProperty> indexedProperties = indexedPropertyRepository.findByIndex(source.getContext().getIndex());
        if (CollectionUtils.isNotEmpty(indexedProperties)) {
            final SuggestBuilder suggestBuilder = new SuggestBuilder();

            int pageSize = source.getPageSize();
            if (pageSize <= ZERO) {
                final Integer defaultPageSize = source.getContext().getFacetSearchConfig().getSearchConfig().getPageSize();
                if (Objects.nonNull(defaultPageSize) && defaultPageSize > ZERO) {
                    pageSize = defaultPageSize;
                }
            }
            final int suggestSize = pageSize;

            // Suggest on normal field
            indexedProperties.stream()
                    .filter(property -> BooleanUtils.isTrue(property.getUseForAutoCompletion()))
                    .forEach(property ->
                            suggestBuilder.addSuggestion(property.getName(),
                                    createSuggestionBuilder(property, source.getText(), suggestSize))
                    );

            // Suggest on nested field
            final List<IndexedPropertyGroup> indexedPropertyGroup = indexedPropertyGroupStrategy.group(indexedProperties);
            indexedPropertyGroup.stream()
                    .flatMap(nestedProperty -> nestedProperty.getNestedProperties().stream())
                    .filter(nestedProperty -> BooleanUtils.isTrue(nestedProperty.getUseForAutoCompletion()))
                    .forEach(nestedProperty ->
                            suggestBuilder.addSuggestion(nestedProperty.getName(),
                                    createSuggestionBuilder(nestedProperty, source.getText(), suggestSize))
                    );

            target.suggest(suggestBuilder);
            target.fetchSource(false);
        }
    }

    private SuggestionBuilder<?> createSuggestionBuilder(final EsIndexedProperty property, final String suggestionText,
                                                      final int suggestSize) {
        SuggestionBuilder<?> suggestionBuilder = null;
        if (Objects.equals(property.getType(), EsPropertyType.COMPLETION)) {
            // Suggest on the field itself
            suggestionBuilder = SuggestBuilders.completionSuggestion(property.getName()).prefix(suggestionText);
        } else if (Objects.equals(property.getMultiFieldsType(), EsPropertyType.COMPLETION)) {
            // Suggest on multi fields
            suggestionBuilder = SuggestBuilders.completionSuggestion(StringUtils.join(property.getName(), DOT,
                    property.getMultiFieldsType().getValue())).prefix(suggestionText);
        }

        if (suggestionBuilder != null) {
            suggestionBuilder.size(suggestSize);
        }
        return suggestionBuilder;
    }
}
