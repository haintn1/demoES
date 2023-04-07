package digi.ecomm.searchstandardapi;

import digi.ecomm.commercesearch.SearchProperties;
import digi.ecomm.commercesearch.client.EsContextFactory;
import digi.ecomm.commercesearch.index.provider.data.IndexedProduct;
import digi.ecomm.commercesearch.index.service.IndexService;
import digi.ecomm.commercesearch.provider.FieldNameResolver;
import digi.ecomm.commercesearch.provider.IndexedSourceProvider;
import digi.ecomm.commercesearch.provider.SortFieldNameResolver;
import digi.ecomm.commercesearch.provider.impl.ExternalIndexedProductProviderImpl;
import digi.ecomm.commercesearch.provider.impl.FieldNameResolverImpl;
import digi.ecomm.commercesearch.provider.impl.SortFieldNameResolverImpl;
import digi.ecomm.commercesearch.repository.EsSortRepository;
import digi.ecomm.commercesearch.search.data.SearchQueryData;
import digi.ecomm.commercesearch.search.data.SearchResponseWrapper;
import digi.ecomm.commercesearch.search.service.SearchService;
import digi.ecomm.commercesearch.strategy.EsFacetSearchConfigSelectionStrategy;
import digi.ecomm.datatransferobject.product.response.B2bProduct;
import digi.ecomm.datatransferobject.search.response.ProductSearchResponse;
import digi.ecomm.datatransferobject.search.response.ProductSuggestionResponse;
import digi.ecomm.platformservice.converter.Converter;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.converter.impl.GenericConverter;
import digi.ecomm.platformservice.cronjob.service.CronJobService;
import digi.ecomm.searchstandardapi.cronjob.impl.IndexingCronjobSampleData;
import digi.ecomm.searchstandardapi.facade.ProductIndexFacade;
import digi.ecomm.searchstandardapi.facade.ProductSearchFacade;
import digi.ecomm.searchstandardapi.facade.SearchQueryCodec;
import digi.ecomm.searchstandardapi.facade.impl.ProductIndexFacadeImpl;
import digi.ecomm.searchstandardapi.facade.impl.ProductSearchFacadeImpl;
import digi.ecomm.searchstandardapi.populator.ProductSearchFacetResponsePopulator;
import digi.ecomm.searchstandardapi.populator.ProductSearchResponsePopulator;
import digi.ecomm.searchstandardapi.populator.ProductSearchSortResponsePopulator;
import digi.ecomm.searchstandardapi.populator.ProductSuggestionResponsePopulator;
import digi.ecomm.searchstandardapi.provider.impl.PriceGroupAwareFieldNameProvider;
import digi.ecomm.searchstandardapi.provider.impl.PriceGroupAwareSortFieldNameProvider;
import digi.ecomm.searchstandardapi.provider.populator.IndexedProductPopulator;
import digi.ecomm.searchstandardapi.strategy.SearchQueryCodecImpl;
import digi.ecomm.searchstandardapi.strategy.StaticProductFacetSearchConfigSelectionStrategyImpl;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
public class SearchStandardApiConfiguration {

    private final SearchProperties searchProperties;

    @Bean("priceGroupAwareFieldNameProvider")
    public PriceGroupAwareFieldNameProvider priceGroupAwareFieldNameProvider() {
        return new PriceGroupAwareFieldNameProvider();
    }

    @Bean("priceGroupAwareSortFieldNameProvider")
    public PriceGroupAwareSortFieldNameProvider priceGroupAwareSortFieldNameProvider() {
        return new PriceGroupAwareSortFieldNameProvider();
    }

    @Bean("fieldNameResolver")
    public FieldNameResolver fieldNameResolver() {
        return new FieldNameResolverImpl();
    }

    @Bean("sortFieldNameResolver")
    public SortFieldNameResolver sortFieldNameResolver() {
        return new SortFieldNameResolverImpl();
    }

    @Bean({"defaultProductIndexFacade", "productIndexFacade"})
    public ProductIndexFacade indexFacade(
            final IndexService indexService,
            final EsContextFactory contextFactory,
            final EsFacetSearchConfigSelectionStrategy facetSearchConfigSelectionStrategy) {
        return new ProductIndexFacadeImpl(indexService, contextFactory, facetSearchConfigSelectionStrategy);
    }

    @Bean({"defaultProductSearchFacade", "productSearchFacade"})
    public ProductSearchFacade searchFacade(final EsFacetSearchConfigSelectionStrategy facetSearchConfigSelectionStrategy,
                                            final SearchQueryCodec<SearchQueryData> searchQueryCodec, final SearchService searchService,
                                            final EsContextFactory contextFactory,
                                            final Converter<SearchResponse, ProductSuggestionResponse> productSuggestionResponseConverter,
                                            final Converter<SearchResponseWrapper, ProductSearchResponse> productSearchResponseConverter) {
        return new ProductSearchFacadeImpl(facetSearchConfigSelectionStrategy, searchQueryCodec, searchService, contextFactory,
                productSuggestionResponseConverter, productSearchResponseConverter);
    }

    @Bean("searchQueryCodec")
    public SearchQueryCodec<SearchQueryData> searchQueryCodec() {
        return new SearchQueryCodecImpl();
    }

    @Bean("externalIndexedProductProvider")
    public IndexedSourceProvider<IndexedProduct> externalIndexedProductProvider(
            final RestTemplate restTemplate, final Converter<B2bProduct, IndexedProduct> indexedProductSourceConverter) {
        return new ExternalIndexedProductProviderImpl(restTemplate, searchProperties, indexedProductSourceConverter);
    }

    @Bean("indexedProductSourceConverter")
    public Converter<B2bProduct, IndexedProduct> indexedProductSourceConverter(
            final Populator<B2bProduct, IndexedProduct> indexedProductSourcePopulator) {

        final GenericConverter<B2bProduct, IndexedProduct> converter = new GenericConverter<>();
        converter.setTargetClass(IndexedProduct.class);
        converter.setPopulators(Arrays.asList(indexedProductSourcePopulator));
        return converter;
    }

    @Bean("indexedProductSourcePopulator")
    public Populator<B2bProduct, IndexedProduct> indexedProductSourcePopulator() {
        return new IndexedProductPopulator();
    }

    @Bean("staticProductFacetSearchConfigSelectionStrategy")
    public EsFacetSearchConfigSelectionStrategy staticProductFacetSearchConfigSelectionStrategy() {
        return new StaticProductFacetSearchConfigSelectionStrategyImpl(searchProperties);
    }

    @Bean("productSuggestionResponseConverter")
    public Converter<SearchResponse, ProductSuggestionResponse> productSuggestionResponseConverter(
            final Populator<SearchResponse, ProductSuggestionResponse> productSuggestionResponsePopulator) {

        final GenericConverter<SearchResponse, ProductSuggestionResponse> converter = new GenericConverter<>();
        converter.setTargetClass(ProductSuggestionResponse.class);
        converter.setPopulators(Arrays.asList(productSuggestionResponsePopulator));
        return converter;
    }

    @Bean("productSuggestionResponsePopulator")
    public Populator<SearchResponse, ProductSuggestionResponse> productSuggestionResponsePopulator() {
        return new ProductSuggestionResponsePopulator();
    }

    @Bean("productSearchResponseConverter")
    public Converter<SearchResponseWrapper, ProductSearchResponse> productSearchResponseConverter(
            final Populator<SearchResponseWrapper, ProductSearchResponse> productSearchResponsePopulator,
            final Populator<SearchResponseWrapper, ProductSearchResponse> productSearchSortResponsePopulator,
            final Populator<SearchResponseWrapper, ProductSearchResponse> productSearchFacetResponsePopulator) {

        final GenericConverter<SearchResponseWrapper, ProductSearchResponse> converter = new GenericConverter<>();
        converter.setTargetClass(ProductSearchResponse.class);
        converter.setPopulators(Arrays.asList(productSearchResponsePopulator, productSearchSortResponsePopulator,
                productSearchFacetResponsePopulator));
        return converter;
    }

    @Bean("productSearchResponsePopulator")
    public Populator<SearchResponseWrapper, ProductSearchResponse> productSearchResponsePopulator(
            final FieldNameResolver fieldNameResolver) {
        return new ProductSearchResponsePopulator(fieldNameResolver);
    }

    @Bean("productSearchSortResponsePopulator")
    public Populator<SearchResponseWrapper, ProductSearchResponse> productSearchSortResponsePopulator(
            final EsSortRepository sortRepository) {

        return new ProductSearchSortResponsePopulator(sortRepository);
    }

    @Bean("productSearchFacetResponsePopulator")
    public Populator<SearchResponseWrapper, ProductSearchResponse> productSearchFacetResponsePopulator() {
        return new ProductSearchFacetResponsePopulator();
    }

    // searchStandardApi CronJob
    @Bean("searchStandardApiCronJob")
    public IndexingCronjobSampleData searchStandardApiCronJob(final CronJobService cronJobService) {
        return new IndexingCronjobSampleData(cronJobService);
    }
}
