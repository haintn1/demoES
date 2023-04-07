package digi.ecomm.elasticpathpcm.sync.fetch.strategy;

import digi.ecomm.elasticpathpcm.api.ApiContextAware;
import digi.ecomm.elasticpathpcm.sync.fetch.EpPageableFetchResult;
import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.base.MetaModel;
import digi.ecomm.elasticpathsdk.model.product.*;
import digi.ecomm.elasticpathsdk.service.Query;
import digi.ecomm.elasticpathsdk.service.pcm.product.ProductApi;
import digi.ecomm.elasticpathsdk.service.pcm.product.impl.ProductApiImpl;
import digi.ecomm.entity.catalog.Catalog;
import digi.ecomm.entity.media.ExternalHostedMedia;
import digi.ecomm.entity.media.Media;
import digi.ecomm.entity.pcm.Product;
import digi.ecomm.entity.pcm.Variation;
import digi.ecomm.pcm.service.CatalogService;
import digi.ecomm.pcm.service.ProductService;
import digi.ecomm.pcm.service.VariationService;
import digi.ecomm.pcm.sync.fetch.strategy.AbstractFetchStrategy;
import digi.ecomm.platformservice.media.service.MediaService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;

import java.util.*;
import java.util.stream.Collectors;

public class EpProductFetchStrategy extends AbstractFetchStrategy<Product> implements ApiContextAware {
    private final ProductService productService;
    private final CatalogService catalogService;
    private final MediaService mediaService;
    private final VariationService variationService;
    private final ProductApi productApi;
    private ApiContext context;

    public EpProductFetchStrategy(final ProductService productService, final CatalogService catalogService,
                                  final MediaService mediaService, final VariationService variationService,
                                  final RequestConfig requestConfig) {
        this.productService = productService;
        this.catalogService = catalogService;
        this.mediaService = mediaService;
        this.variationService = variationService;
        this.productApi = new ProductApiImpl(requestConfig);
    }

    /**
     * Fetch products from EP.
     *
     * @return
     */
    @InjectApiContext
    public EpPageableFetchResult<Product> fetch(final int currentPage) {
        final Query query = Query.builder().currentPage(currentPage).include(ProductApi.MAIN_IMAGES).build();
        final ProductsModel productsModel = productApi.getAll(query, context);
        final Catalog productCatalog = catalogService.getCurrentCatalog();
        List<Product> results;
        if (CollectionUtils.isNotEmpty(productsModel.getData())) {
            results = processInternal(productsModel.getData().stream()
                    .map(productData -> {
                        final Product product = new Product();
                        product.setExternalId(productData.getId());
                        product.setCode(productData.getAttributes().getSku());
                        product.setName(productData.getAttributes().getName());
                        product.setDescription(productData.getAttributes().getDescription());
                        product.setCatalog(productCatalog);
                        Optional.ofNullable(productsModel.getInclude())
                                .map(ProductIncludeModel::getMainImages)
                                .ifPresent(mainImages ->
                                        product.setMainImage(extractMainImage(productCatalog, productData, mainImages)));
                        Optional.ofNullable(productData.getMeta())
                                .map(ProductMetaModel::getVariations)
                                .ifPresent(variations -> product.setVariations(extractVariations(variations)));
                        return product;
                    }).collect(Collectors.toList()));
        } else {
            results = Collections.emptyList();
        }
        final Double totalPages = Math.ceil(
                Optional.ofNullable(productsModel.getMeta())
                        .map(MetaModel::getResults)
                        .map(MetaModel.Results::getTotal)
                        .orElse(0L) / (double) query.getPageable().getPageSize());
        return new EpPageableFetchResult<>(results, currentPage, totalPages.longValue());
    }

    private Set<Variation> extractVariations(final List<ProductMetaModel.Variation> variations) {
        if (CollectionUtils.isNotEmpty(variations)) {
            return new HashSet<>(variationService.getAllByExternalIds(
                    variations.stream().map(ProductMetaModel.Variation::getId).collect(Collectors.toList())));
        }
        return Collections.emptySet();
    }

    private Media extractMainImage(final Catalog productCatalog, final ProductDataModel productData,
                                   final List<ProductIncludeMainImageModel> mainImages) {
        if (productData.getRelationships() != null) {
            final String mainImageId = productData.getRelationships().getMainImageId();
            if (StringUtils.isNotBlank(mainImageId) && CollectionUtils.isNotEmpty(mainImages)) {
                final Optional<ProductIncludeMainImageModel> mainImageOpt = mainImages.stream()
                        .filter(imageData -> StringUtils.equals(mainImageId, imageData.getId()))
                        .findFirst();
                if (mainImageOpt.isPresent()) {
                    final ProductIncludeMainImageModel imageData = mainImageOpt.get();
                    ExternalHostedMedia mainImage = mediaService.getByExternalId(imageData.getId(), ExternalHostedMedia.class);
                    if (mainImage == null) {
                        mainImage = new ExternalHostedMedia();
                    }
                    mainImage.setExternalId(imageData.getId());
                    mainImage.setCode(imageData.getId());
                    mainImage.setFileSizeInByte(imageData.getFileSizeInByte());
                    mainImage.setUrl(imageData.getHref());
                    mainImage.setFileName(imageData.getFileName());
                    mainImage.setRealFileName(imageData.getFileName());
                    mainImage.setAltText(imageData.getFileName());
                    mainImage.setDescription(imageData.getFileName());
                    mainImage.setCatalog(productCatalog);
                    getEntityService().save(mainImage);
                    return mainImage;
                }
            }
        }
        return null;
    }

    @Override
    protected List<Product> getEntitiesByExternalIds(final List<String> externalIds) {
        return productService.getAllByExternalIds(externalIds);
    }

    @Override
    protected void copyProperties(final Product source, final Product target) {
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setMainImage(source.getMainImage());
        target.setVariations(source.getVariations());
    }

    @Override
    public void setApiContext(final ApiContext context) {
        this.context = context;
    }
}
