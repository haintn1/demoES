package digi.ecomm.elasticpathpcm.sync.fetch.service.impl;

import digi.ecomm.elasticpathpcm.sync.fetch.EpPageableFetchResult;
import digi.ecomm.elasticpathpcm.sync.fetch.strategy.EpProductFetchStrategy;
import digi.ecomm.elasticpathpcm.sync.fetch.strategy.EpVariationFetchStrategy;
import digi.ecomm.elasticpathpcm.sync.fetch.strategy.EpVariationOptionFetchStrategy;
import digi.ecomm.entity.pcm.Product;
import digi.ecomm.entity.pcm.Variation;
import digi.ecomm.entity.pcm.VariationOption;
import digi.ecomm.pcm.sync.fetch.service.FetchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

public class EpFetchServiceImpl implements FetchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EpFetchServiceImpl.class);

    @Resource
    private EpVariationFetchStrategy variationFetchStrategy;

    @Resource
    private EpVariationOptionFetchStrategy variationOptionFetchStrategy;

    @Resource
    private EpProductFetchStrategy productFetchStrategy;

    @Override
    public final void fetch() {
        // Variations
        final List<Variation> variations = variationFetchStrategy.fetch();
        LOGGER.info("Fetched {} variations", variations.size());

        // Variation options
        final List<VariationOption> options = variationOptionFetchStrategy.fetch(variations);
        LOGGER.info("Fetched {} variation options", options.size());

        long productTotalPages = 0;
        int productPage = 0;
        do {
            final EpPageableFetchResult<Product> fetchResult = productFetchStrategy.fetch(productPage++);
            productTotalPages = fetchResult.getTotalPages();
            LOGGER.info("Fetched {} products", fetchResult.getResults().size());
        } while (productPage <= productTotalPages);
    }
}
