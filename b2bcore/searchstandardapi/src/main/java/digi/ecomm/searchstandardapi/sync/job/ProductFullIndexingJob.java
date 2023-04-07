package digi.ecomm.searchstandardapi.sync.job;

import digi.ecomm.commercesearch.exception.NoValidElasticsearchConfigException;
import digi.ecomm.commercesearch.index.service.IndexService;
import digi.ecomm.entity.index.IndexCronJob;
import digi.ecomm.platformservice.cronjob.job.AbstractCronJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.io.IOException;

@DisallowConcurrentExecution
public class ProductFullIndexingJob extends AbstractCronJob<IndexCronJob> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductFullIndexingJob.class);

    @Resource
    private IndexService indexService;


    @Override
    protected void executeInternal(final JobExecutionContext context, final IndexCronJob cronJob)
            throws JobExecutionException {
        LOGGER.info("Full indexing ...");
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        context.getJobInstance();
        try {
            indexService.fullIndexing();
        } catch (NoValidElasticsearchConfigException | IOException e) {
            LOGGER.error("Failed to perform full index", e.getMessage());
        }
        stopWatch.stop();
        LOGGER.info("Done.");
        LOGGER.info("Execution time: {}", stopWatch.getTotalTimeMillis());
    }
}
