package digi.ecomm.elasticpathpcm;

import digi.ecomm.elasticpathpcm.api.ApiContextFactory;
import digi.ecomm.elasticpathpcm.api.impl.ApiContextFactoryImpl;
import digi.ecomm.elasticpathpcm.initialdata.EpCronJobSampleData;
import digi.ecomm.elasticpathpcm.sync.fetch.strategy.EpProductFetchStrategy;
import digi.ecomm.elasticpathpcm.sync.fetch.strategy.EpVariationFetchStrategy;
import digi.ecomm.elasticpathpcm.sync.fetch.strategy.EpVariationOptionFetchStrategy;
import digi.ecomm.elasticpathpcm.sync.push.strategy.EpVariationPushStrategy;
import digi.ecomm.pcm.service.CatalogService;
import digi.ecomm.pcm.service.ProductService;
import digi.ecomm.pcm.service.VariationService;
import digi.ecomm.pcm.sync.fetch.service.FetchService;
import digi.ecomm.elasticpathpcm.sync.fetch.service.impl.EpFetchServiceImpl;
import digi.ecomm.pcm.sync.push.handler.VariationEventHandler;
import digi.ecomm.pcm.sync.push.strategy.VariationPushStrategy;
import digi.ecomm.platformservice.cronjob.service.CronJobService;
import digi.ecomm.platformservice.media.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class EpPcmConfiguration {

    private final VariationService variationService;
    private final ProductService productService;
    private final CatalogService catalogService;
    private final MediaService mediaService;
    private final EpProperties epProperties;

    // SAMPLE DATA
    @Bean("epCronJobSampleData")
    public EpCronJobSampleData epCronJobSampleData(final CronJobService cronJobService) {
        return new EpCronJobSampleData(cronJobService);
    }

    @Bean("apiContextFactory")
    public ApiContextFactory apiContextFactory() {
        return new ApiContextFactoryImpl(epProperties);
    }

    @Bean("requestConfig")
    public RequestConfig requestConfig() {
        return RequestConfig.custom().setConnectTimeout(epProperties.getApi().getConnectTimeout())
                .setSocketTimeout(epProperties.getApi().getSocketTimeout()).build();
    }

    // FETCH
    @Bean("fetchService")
    public FetchService fetchService() {
        return new EpFetchServiceImpl();
    }

    @Bean("variationFetchStrategy")
    public EpVariationFetchStrategy variationFetchStrategy() {
        return new EpVariationFetchStrategy(variationService, requestConfig());
    }

    @Bean("variationOptionFetchStrategy")
    public EpVariationOptionFetchStrategy variationOptionFetchStrategy() {
        return new EpVariationOptionFetchStrategy(variationService, requestConfig());
    }

    @Bean("productFetchStrategy")
    public EpProductFetchStrategy productFetchStrategy() {
        return new EpProductFetchStrategy(productService, catalogService, mediaService, variationService, requestConfig());
    }

    // PUSH
    @Bean("variationEventHandler")
    public VariationEventHandler variationEventHandler() {
        return new VariationEventHandler();
    }

    @Bean("variationPushStrategy")
    public VariationPushStrategy variationPushStrategy() {
        return new EpVariationPushStrategy(requestConfig());
    }
}
