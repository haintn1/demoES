package digi.ecomm.pcm.sync.push.job;

import digi.ecomm.entity.cronjob.CronJob;
import digi.ecomm.pcm.service.SyncLogService;
import digi.ecomm.platformservice.cronjob.job.AbstractCronJob;
import lombok.RequiredArgsConstructor;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

@RequiredArgsConstructor
@DisallowConcurrentExecution
public class SynchronizationRetryJob extends AbstractCronJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizationRetryJob.class);

    @Resource
    private SyncLogService syncLogService;

    @Override
    protected void executeInternal(final JobExecutionContext context, final CronJob cronJob) {
        LOGGER.info("Retrying synchronization ...");
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final long totalRecords = 0;
        stopWatch.stop();
        LOGGER.info("Done.");
        LOGGER.info("Total: {} records. Execution time: {}", totalRecords, stopWatch.getTotalTimeMillis());
    }
}
