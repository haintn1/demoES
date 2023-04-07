package digi.ecomm.platformservice.cronjob.listener;

import digi.ecomm.entity.cronjob.CronJob;
import digi.ecomm.entity.cronjob.JobState;
import digi.ecomm.platformservice.cronjob.repository.CronJobRepository;
import digi.ecomm.platformservice.persistent.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.listeners.JobListenerSupport;

@RequiredArgsConstructor
public class JobStateListener extends JobListenerSupport {

    private final EntityService entityService;
    private final CronJobRepository cronJobRepository;

    @Override
    public String getName() {
        return "Job state listener";
    }

    @Override
    public void jobToBeExecuted(final JobExecutionContext context) {
        super.jobToBeExecuted(context);
        final JobKey jobKey = context.getJobDetail().getKey();
        final CronJob executingJob = cronJobRepository.findByJobNameAndJobGroup(jobKey.getName(), jobKey.getGroup());
        executingJob.setJobStatus(JobState.RUNNING);
        entityService.save(executingJob);
    }

    @Override
    public void jobWasExecuted(final JobExecutionContext context, final JobExecutionException jobException) {
        super.jobWasExecuted(context, jobException);
        final JobKey jobKey = context.getJobDetail().getKey();
        final CronJob executingJob = cronJobRepository.findByJobNameAndJobGroup(jobKey.getName(), jobKey.getGroup());
        executingJob.setJobStatus(JobState.COMPLETE);
        entityService.save(executingJob);
    }
}
