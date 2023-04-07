package digi.ecomm.platformservice.cronjob.job;

import digi.ecomm.entity.cronjob.CronJob;
import digi.ecomm.platformservice.cronjob.service.CronJobService;
import digi.ecomm.platformservice.persistent.service.EntityService;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;

public abstract class AbstractCronJob<T extends CronJob> extends QuartzJobBean implements InterruptableJob {

    private boolean stop = false;

    @Resource
    private EntityService entityService;

    @Resource
    private CronJobService cronJobService;

    @Override
    protected final void executeInternal(final JobExecutionContext context) throws JobExecutionException {
        final String jobName = context.getJobDetail().getKey().getName();
        final String jobGroup = context.getJobDetail().getKey().getGroup();
        final CronJob cronJob = cronJobService.getJob(jobName, jobGroup);
        executeInternal(context, (T) cronJob);
    }

    /**
     * Execute the actual job. The job data map will already have been
     * applied as bean property values by execute. The contract is
     * exactly the same as for the standard Quartz execute method.
     *
     * @param context
     * @param cronJob
     * @see #execute
     */
    protected abstract void executeInternal(JobExecutionContext context, T cronJob) throws JobExecutionException;

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        this.stop = true;
    }

    /**
     * If the cronjob is suggested to stop current execution.
     *
     * @return
     */
    protected boolean shouldStop() {
        return this.stop;
    }

    public EntityService getEntityService() {
        return entityService;
    }
}
