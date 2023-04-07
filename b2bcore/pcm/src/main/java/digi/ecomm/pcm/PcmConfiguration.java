package digi.ecomm.pcm;

import digi.ecomm.pcm.initialdata.*;
import digi.ecomm.pcm.repository.*;
import digi.ecomm.pcm.service.CatalogService;
import digi.ecomm.pcm.service.ProductService;
import digi.ecomm.pcm.service.SyncLogService;
import digi.ecomm.pcm.service.VariationService;
import digi.ecomm.pcm.service.impl.CatalogServiceImpl;
import digi.ecomm.pcm.service.impl.ProductServiceImpl;
import digi.ecomm.pcm.service.impl.SyncLogServiceImpl;
import digi.ecomm.pcm.service.impl.VariationServiceImpl;
import digi.ecomm.platformservice.cronjob.service.CronJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@RequiredArgsConstructor
@Configuration
public class PcmConfiguration {

    private final CatalogRepository catalogRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final AttributeUnitRepository attributeUnitRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeTemplateRepository attributeTemplateRepository;
    private final ProductFeatureRepository productFeatureRepository;
    private final VariationRepository variationRepository;
    private final VariationOptionRepository variationOptionRepository;
    private final SyncLogRepository syncLogRepository;

    @Bean("catalogService")
    public CatalogService catalogService() {
        return new CatalogServiceImpl(catalogRepository);
    }

    @Bean("productService")
    public ProductService productService() {
        return new ProductServiceImpl(productRepository);
    }

    @Bean("variationService")
    public VariationService variationService() {
        return new VariationServiceImpl(variationRepository, variationOptionRepository);
    }

    @Bean("syncLogService")
    public SyncLogService syncLogService() {
        return new SyncLogServiceImpl(syncLogRepository);
    }

    // SAMPLE DATA
    @Bean("cronJobSampleData")
    public CronJobSampleData cronJobSampleData(final CronJobService cronJobService) {
        return new CronJobSampleData(cronJobService);
    }

    @Bean("catalogSampleData")
    public CatalogSampleData catalogSampleData() {
        return new CatalogSampleData(catalogRepository);
    }

    @DependsOn("catalogSampleData")
    @Bean("categorySampleData")
    public CategorySampleData categorySampleData() {
        return new CategorySampleData(catalogRepository, categoryRepository);
    }

    @DependsOn("categorySampleData")
    @Bean("productSampleData")
    public ProductSampleData productSampleData() {
        return new ProductSampleData(catalogRepository, categoryRepository, productRepository);
    }

    @DependsOn("productSampleData")
    @Bean("productFeatureSampleData")
    public ProductFeatureSampleData productFeatureSampleData() {
        return new ProductFeatureSampleData(catalogRepository, categoryRepository, productRepository, attributeUnitRepository,
                attributeRepository, attributeTemplateRepository, productFeatureRepository);
    }
}
