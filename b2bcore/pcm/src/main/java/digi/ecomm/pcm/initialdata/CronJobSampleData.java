package digi.ecomm.pcm.initialdata;

import digi.ecomm.entity.cronjob.CronJob;
import digi.ecomm.pcm.sync.push.job.SynchronizationLogCleanJob;
import digi.ecomm.pcm.sync.push.job.SynchronizationRetryJob;
import digi.ecomm.platformservice.cronjob.service.CronJobService;
import digi.ecomm.platformservice.initialdata.setup.SampleDataCreator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CronJobSampleData implements SampleDataCreator {
    private static final String PCM_JOB_GROUP = "PcmGroup";
    private static final String SYNC_LOG_CLEAN_JOB_NAME = "SyncLogCleanJob";
    private static final String SYNC_RETRY_JOB_NAME = "SyncRetryJob";

    private final CronJobService cronJobService;

    @Override
    public void createData() {
        if (cronJobService.getJob(SYNC_LOG_CLEAN_JOB_NAME, PCM_JOB_GROUP) == null) {
            final CronJob cleanCronJob = new CronJob();
            cleanCronJob.setJobName(SYNC_LOG_CLEAN_JOB_NAME);
            cleanCronJob.setJobGroup(PCM_JOB_GROUP);
            cleanCronJob.setJobClass(SynchronizationLogCleanJob.class.getName());
            cleanCronJob.setCronExpression("0 0 0 ? * SUN");
            cleanCronJob.setDescription("Cron job for cleaning synchronization logs");
            cronJobService.saveJob(cleanCronJob);
        }

        if (cronJobService.getJob(SYNC_RETRY_JOB_NAME, PCM_JOB_GROUP) == null) {
            final CronJob retryCronJob = new CronJob();
            retryCronJob.setJobName(SYNC_RETRY_JOB_NAME);
            retryCronJob.setJobGroup(PCM_JOB_GROUP);
            retryCronJob.setJobClass(SynchronizationRetryJob.class.getName());
            retryCronJob.setCronExpression("0 0 0 * * ?");
            retryCronJob.setDescription("Cron job for retrying synchronizations");
            cronJobService.saveJob(retryCronJob);
        }
    }
}
