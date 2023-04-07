package digi.ecomm.elasticpathpcm.initialdata;

import digi.ecomm.elasticpathpcm.sync.fetch.job.SyncFetchJob;
import digi.ecomm.entity.cronjob.CronJob;
import digi.ecomm.platformservice.cronjob.service.CronJobService;
import digi.ecomm.platformservice.initialdata.setup.SampleDataCreator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EpCronJobSampleData implements SampleDataCreator {
    private static final String PCM_JOB_GROUP = "PcmGroup";
    private static final String SYNC_FETCH_JOB_NAME = "SyncFetchJob";

    private final CronJobService cronJobService;

    @Override
    public void createData() {
        if (cronJobService.getJob(SYNC_FETCH_JOB_NAME, PCM_JOB_GROUP) == null) {
            final CronJob retryCronJob = new CronJob();
            retryCronJob.setJobName(SYNC_FETCH_JOB_NAME);
            retryCronJob.setJobGroup(PCM_JOB_GROUP);
            retryCronJob.setJobClass(SyncFetchJob.class.getName());
            retryCronJob.setCronExpression("0 0 3 * * ?");
            retryCronJob.setDescription("Cron job for fetching data from third party");
            cronJobService.saveJob(retryCronJob);
        }
    }
}
