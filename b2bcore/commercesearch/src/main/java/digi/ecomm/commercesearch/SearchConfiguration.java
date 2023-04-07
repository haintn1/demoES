package digi.ecomm.commercesearch;

import digi.ecomm.commercesearch.client.EsClientFactory;
import digi.ecomm.commercesearch.provider.FieldNameResolver;
import digi.ecomm.commercesearch.provider.SortFieldNameResolver;
import digi.ecomm.commercesearch.repository.*;
import digi.ecomm.commercesearch.search.data.SearchQueryPageableData;
import digi.ecomm.commercesearch.search.data.SuggestionQueryData;
import digi.ecomm.commercesearch.search.populator.*;
import digi.ecomm.commercesearch.search.service.SearchService;
import digi.ecomm.commercesearch.search.service.impl.SearchServiceImpl;
import digi.ecomm.commercesearch.strategy.EsAggregationBuilderFactory;
import digi.ecomm.commercesearch.strategy.EsBoostRuleFilterBuilderFactory;
import digi.ecomm.commercesearch.strategy.EsFilterQueryBuilderFactory;
import digi.ecomm.commercesearch.strategy.EsIndexedPropertyGroupStrategy;
import digi.ecomm.commercesearch.strategy.impl.EsAggregationBuilderFactoryImpl;
import digi.ecomm.commercesearch.strategy.impl.EsBoostRuleFilterBuilderFactoryImpl;
import digi.ecomm.commercesearch.strategy.impl.EsFilterQueryBuilderFactoryImpl;
import digi.ecomm.platformservice.converter.Converter;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.converter.impl.GenericConverter;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SearchConfiguration {

    @Bean("searchService")
    public SearchService searchService(
            final EsClientFactory clientFactory,
            final Converter<SearchQueryPageableData, SearchRequest> searchRequestConverter,
            final Converter<SuggestionQueryData, SearchRequest> suggestionSearchRequestConverter) {

        return new SearchServiceImpl(clientFactory, searchRequestConverter, suggestionSearchRequestConverter);
    }

    /* Free text search*/

    @Bean("aggregationBuilderFactory")
    public EsAggregationBuilderFactory aggregationBuilderFactory(final EsValueRangeRepository valueRangeRepository,
                                                                 final FieldNameResolver fieldNameResolver) {
        return new EsAggregationBuilderFactoryImpl(valueRangeRepository, fieldNameResolver);
    }

    @Bean("filterQueryBuilderFactory")
    public EsFilterQueryBuilderFactory filterQueryBuilderFactory(final FieldNameResolver fieldNameResolver) {
        return new EsFilterQueryBuilderFactoryImpl(fieldNameResolver);
    }

    @Bean("boostRuleFilterBuilderFactory")
    public EsBoostRuleFilterBuilderFactory boostRuleFilterBuilderFactory() {
        return new EsBoostRuleFilterBuilderFactoryImpl();
    }

    @Bean("searchRequestConverter")
    public Converter<SearchQueryPageableData, SearchRequest> searchRequestConverter(
            final Populator<SearchQueryPageableData, SearchRequest> searchRequestPopulator) {

        final GenericConverter<SearchQueryPageableData, SearchRequest> converter = new GenericConverter<>();
        converter.setTargetClass(SearchRequest.class);
        converter.setPopulators(Arrays.asList(searchRequestPopulator));
        return converter;
    }

    @Bean("searchRequestPopulator")
    public Populator<SearchQueryPageableData, SearchRequest> searchRequestPopulator(
            final Converter<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderConverter) {

        return new SearchRequestPopulator(searchSourceBuilderConverter);
    }

    @Bean("searchSourceBuilderConverter")
    public Converter<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderConverter(
            final Populator<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderQueryPopulator,
            final Populator<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderBoostRulePopulator,
            final Populator<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderPagingPopulator,
            final Populator<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderPromotedItemPopulator,
            final Populator<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderSortPopulator,
            final Populator<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderFacetPopulator) {

        final GenericConverter<SearchQueryPageableData, SearchSourceBuilder> converter = new GenericConverter<>();
        converter.setTargetClass(SearchSourceBuilder.class);
        converter.setPopulators(Arrays.asList(searchSourceBuilderQueryPopulator, searchSourceBuilderBoostRulePopulator,
                searchSourceBuilderPagingPopulator, searchSourceBuilderPromotedItemPopulator, searchSourceBuilderSortPopulator,
                searchSourceBuilderFacetPopulator));
        return converter;
    }

    @Bean("searchSourceBuilderQueryPopulator")
    public Populator<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderQueryPopulator(
            final EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy,
            final EsFilterQueryBuilderFactory filterQueryBuilderFactory,
            final FieldNameResolver fieldNameResolver) {

        return new SearchSourceBuilderQueryPopulator(indexedPropertyGroupStrategy, filterQueryBuilderFactory,
                fieldNameResolver);
    }

    @Bean("searchSourceBuilderBoostRulePopulator")
    public Populator<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderBoostRulePopulator(
            final EsBoostRuleRepository boostRuleRepository,
            final EsBoostRuleFilterBuilderFactory boostRuleFilterBuilderFactory,
            final EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy) {

        return new SearchSourceBuilderBoostRulePopulator(boostRuleRepository, boostRuleFilterBuilderFactory,
                indexedPropertyGroupStrategy);
    }

    @Bean("searchSourceBuilderPagingPopulator")
    public Populator<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderPagingPopulator() {
        return new SearchSourceBuilderPagingPopulator();
    }

    @Bean("searchSourceBuilderPromotedItemPopulator")
    public Populator<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderPromotedItemPopulator(
            final EsPromotedItemRepository promotedItemRepository) {

        return new SearchSourceBuilderPromotedItemPopulator(promotedItemRepository);
    }

    @Bean("searchSourceBuilderSortPopulator")
    public Populator<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderSortPopulator(
            final EsSortRepository sortRepository,
            final EsSortFieldRepository sortFieldRepository,
            final SortFieldNameResolver sortFieldNameResolver) {

        return new SearchSourceBuilderSortPopulator(sortRepository, sortFieldRepository, sortFieldNameResolver);
    }

    @Bean("searchSourceBuilderFacetPopulator")
    public Populator<SearchQueryPageableData, SearchSourceBuilder> searchSourceBuilderFacetPopulator(
            final EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy,
            final EsAggregationBuilderFactory aggregationBuilderFactory) {

        return new SearchSourceBuilderFacetPopulator(indexedPropertyGroupStrategy, aggregationBuilderFactory);
    }

    /* Suggestion search*/

    @Bean("suggestionSearchRequestConverter")
    public Converter<SuggestionQueryData, SearchRequest> suggestionSearchRequestConverter(
            final Populator<SuggestionQueryData, SearchRequest> suggestionSearchRequestPopulator) {

        final GenericConverter<SuggestionQueryData, SearchRequest> converter = new GenericConverter<>();
        converter.setTargetClass(SearchRequest.class);
        converter.setPopulators(Arrays.asList(suggestionSearchRequestPopulator));
        return converter;
    }

    @Bean("suggestionSearchRequestPopulator")
    public Populator<SuggestionQueryData, SearchRequest> suggestionSearchRequestPopulator(
            final Converter<SuggestionQueryData, SearchSourceBuilder> suggestionSearchSourceBuilderConverter) {

        return new SuggestionSearchRequestPopulator(suggestionSearchSourceBuilderConverter);
    }

    @Bean("suggestionSearchSourceBuilderConverter")
    public Converter<SuggestionQueryData, SearchSourceBuilder> suggestionSearchSourceBuilderConverter(
            final Populator<SuggestionQueryData, SearchSourceBuilder> suggestionSearchSourceBuilderSuggestionPopulator) {

        final GenericConverter<SuggestionQueryData, SearchSourceBuilder> converter = new GenericConverter<>();
        converter.setTargetClass(SearchSourceBuilder.class);
        converter.setPopulators(Arrays.asList(suggestionSearchSourceBuilderSuggestionPopulator));
        return converter;
    }

    @Bean("suggestionSearchSourceBuilderSuggestionPopulator")
    public Populator<SuggestionQueryData, SearchSourceBuilder> suggestionSearchSourceBuilderSuggestionPopulator(
            final EsIndexedPropertyRepository indexedPropertyRepository,
            final EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy) {

        return new SuggestionSearchSourceBuilderSuggestionPopulator(indexedPropertyRepository, indexedPropertyGroupStrategy);
    }
}
