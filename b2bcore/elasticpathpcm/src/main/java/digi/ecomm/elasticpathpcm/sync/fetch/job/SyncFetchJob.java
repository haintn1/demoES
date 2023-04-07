package digi.ecomm.elasticpathpcm.sync.fetch.job;

import digi.ecomm.entity.cronjob.CronJob;
import digi.ecomm.pcm.sync.fetch.service.FetchService;
import digi.ecomm.platformservice.cronjob.job.AbstractCronJob;
import lombok.RequiredArgsConstructor;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

@RequiredArgsConstructor
@DisallowConcurrentExecution
public class SyncFetchJob extends AbstractCronJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(SyncFetchJob.class);

    @Resource
    private FetchService fetchService;

    @Override
    protected void executeInternal(final JobExecutionContext context, final CronJob cronJob) throws JobExecutionException {
        LOGGER.info("Fetching data ...");
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        fetchService.fetch();
        stopWatch.stop();
        LOGGER.info("Done.");
        LOGGER.info("Execution time: {} ms", stopWatch.getTotalTimeMillis());
    }
}
