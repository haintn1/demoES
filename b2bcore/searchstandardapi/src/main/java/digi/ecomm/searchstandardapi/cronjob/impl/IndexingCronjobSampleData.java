package digi.ecomm.searchstandardapi.cronjob.impl;

import digi.ecomm.entity.index.IndexCronJob;
import digi.ecomm.platformservice.cronjob.service.CronJobService;
import digi.ecomm.platformservice.initialdata.setup.SampleDataCreator;
import digi.ecomm.searchstandardapi.sync.job.ProductFullIndexingJob;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class IndexingCronjobSampleData implements SampleDataCreator {
    private static final String INDEXING_GROUP = "indexingGroup";
    private static final String FULL_INDEXING_JOB_NAME = "fullIndexingJob";

    private final CronJobService cronJobService;

    /**
     * Create data.
     */
    @Override
    public void createData() {
        if (cronJobService.getJob(FULL_INDEXING_JOB_NAME, INDEXING_GROUP) == null) {
            final IndexCronJob cronJob = new IndexCronJob();
            cronJob.setJobName(FULL_INDEXING_JOB_NAME);
            cronJob.setJobGroup(INDEXING_GROUP);
            cronJob.setJobClass(ProductFullIndexingJob.class.getName());
            cronJob.setCronExpression("0 0 0 * * ?");
            cronJob.setDescription("Cron job for Full Indexing logs");
            cronJobService.saveJob(cronJob);
        }
    }
}
