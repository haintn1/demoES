package digi.ecomm.platformservice.cronjob.service.impl;

import digi.ecomm.entity.cronjob.CronJob;
import digi.ecomm.entity.cronjob.JobState;
import digi.ecomm.platformservice.cronjob.exception.ScheduleJobException;
import digi.ecomm.platformservice.cronjob.job.JobScheduleCreator;
import digi.ecomm.platformservice.cronjob.listener.JobStateListener;
import digi.ecomm.platformservice.cronjob.repository.CronJobRepository;
import digi.ecomm.platformservice.cronjob.service.CronJobService;
import digi.ecomm.platformservice.exception.UnknownIdentifierException;
import digi.ecomm.platformservice.persistent.service.EntityService;
import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RequiredArgsConstructor
public class CronJobServiceImpl implements CronJobService, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronJobServiceImpl.class);

    private final Scheduler scheduler;
    private final ApplicationContext context;
    private final JobScheduleCreator scheduleCreator;
    private final EntityService entityService;
    private final CronJobRepository cronJobRepository;
    private final JobStateListener jobStateListener;

    @Override
    public SchedulerMetaData getMetaData() throws SchedulerException {
        return scheduler.getMetaData();
    }

    @Transactional
    @Override
    public boolean deleteJob(final Long jobId) {
        ServicesUtils.validateParameterNotNullStandardMessage("jobId", jobId); //NOSONAR

        final CronJob cronJob = entityService.getById(CronJob.class, jobId);
        if (cronJob == null) {
            throw new UnknownIdentifierException(String.format("No job with id=%s found.", jobId)); //NOSONAR
        }

        try {
            entityService.delete(cronJob);
            LOGGER.info("Job {} was deleted.", cronJob.getJobName());
            return scheduler.deleteJob(new JobKey(cronJob.getJobName(), cronJob.getJobGroup()));
        } catch (SchedulerException e) {
            throw new ScheduleJobException(String.format("Failed to delete job %s", cronJob.getJobName()), e);
        }
    }

    @Transactional
    @Override
    public boolean pauseJob(final Long jobId) {
        ServicesUtils.validateParameterNotNullStandardMessage("jobId", jobId); //NOSONAR

        final CronJob cronJob = entityService.getById(CronJob.class, jobId);
        if (cronJob == null) {
            throw new UnknownIdentifierException(String.format("No job with id=%s found.", jobId)); //NOSONAR
        }

        try {
            scheduler.pauseJob(new JobKey(cronJob.getJobName(), cronJob.getJobGroup()));
            cronJob.setJobStatus(JobState.PAUSED);
            entityService.save(cronJob);
            LOGGER.info("Job {} was paused.", cronJob.getJobName());
            return true;
        } catch (SchedulerException e) {
            throw new ScheduleJobException(String.format("Failed to pause job %s", cronJob.getJobName()), e);
        }
    }

    @Transactional
    @Override
    public boolean resumeJob(final Long jobId) {
        ServicesUtils.validateParameterNotNullStandardMessage("jobId", jobId); //NOSONAR

        final CronJob cronJob = entityService.getById(CronJob.class, jobId);
        if (cronJob == null) {
            throw new UnknownIdentifierException(String.format("No job with id=%s found.", jobId)); //NOSONAR
        }

        try {
            scheduler.resumeJob(new JobKey(cronJob.getJobName(), cronJob.getJobGroup()));
            cronJob.setJobStatus(JobState.SCHEDULED);
            entityService.save(cronJob);
            LOGGER.info("Job {} was resumed.", cronJob.getJobName());
            return true;
        } catch (SchedulerException e) {
            throw new ScheduleJobException(String.format("Failed to resume job %s", cronJob.getJobName()), e);
        }
    }

    @Override
    public boolean stopJob(final Long jobId) {
        ServicesUtils.validateParameterNotNullStandardMessage("jobId", jobId); //NOSONAR

        final CronJob cronJob = entityService.getById(CronJob.class, jobId);
        if (cronJob == null) {
            throw new UnknownIdentifierException(String.format("No job with id=%s found.", jobId)); //NOSONAR
        }

        try {
            final boolean interrupted = scheduler.interrupt(new JobKey(cronJob.getJobName(), cronJob.getJobGroup()));
            if (interrupted) {
                cronJob.setJobStatus(JobState.SCHEDULED);
                entityService.save(cronJob);
                LOGGER.info("Job {} was stopped.", cronJob.getJobName());
            }
            return interrupted;
        } catch (UnableToInterruptJobException e) {
            throw new ScheduleJobException(String.format("Failed to stop job %s", cronJob.getJobName()), e);
        }
    }

    @Transactional
    @Override
    public boolean startJobNow(final Long jobId) {
        ServicesUtils.validateParameterNotNullStandardMessage("jobId", jobId); //NOSONAR

        final CronJob cronJob = entityService.getById(CronJob.class, jobId);
        if (cronJob == null) {
            throw new UnknownIdentifierException(String.format("No job with id=%s found.", jobId)); //NOSONAR
        }

        try {
            scheduler.triggerJob(new JobKey(cronJob.getJobName(), cronJob.getJobGroup()));
            cronJob.setJobStatus(JobState.RUNNING);
            entityService.save(cronJob);
            LOGGER.info("Job {} is scheduled and started now.", cronJob.getJobName());
            return true;
        } catch (SchedulerException e) {
            throw new ScheduleJobException(String.format("Failed to start job %s", cronJob.getJobName()), e);
        }
    }

    @Transactional
    @Override
    public void saveJob(final CronJob cronJob) {
        if (cronJob.isNew()) {
            scheduleNewJob(cronJob);
        } else {
            updateScheduleJob(cronJob);
        }
    }

    private void scheduleNewJob(final CronJob cronJob) {
        try {
            JobDetail jobDetail = JobBuilder
                    .newJob((Class<? extends QuartzJobBean>) Class.forName(cronJob.getJobClass()))
                    .withIdentity(cronJob.getJobName(), cronJob.getJobGroup()).build();

            if (!scheduler.checkExists(jobDetail.getKey())) {
                jobDetail = scheduleCreator.createJob(
                        (Class<? extends QuartzJobBean>) Class.forName(cronJob.getJobClass()), false, context,
                        cronJob.getJobName(), cronJob.getJobGroup());

                final Trigger trigger = scheduleCreator.createCronTrigger(cronJob.getJobName(), new Date(),
                        cronJob.getCronExpression(), CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
                scheduler.scheduleJob(jobDetail, trigger);
                cronJob.setJobStatus(JobState.SCHEDULED);
                entityService.save(cronJob);
            }
        } catch (SchedulerException e) {
            throw new ScheduleJobException(String.format("Failed to schedule job %s", cronJob.getJobName()), e);
        } catch (ClassNotFoundException e) {
            throw new ScheduleJobException(String.format("Invalid class % for job %s", cronJob.getClass(),
                    cronJob.getJobName()), e);
        }
    }

    private void updateScheduleJob(final CronJob cronJob) {
        try {
            final Trigger newTrigger = scheduleCreator.createCronTrigger(cronJob.getJobName(), new Date(),
                    cronJob.getCronExpression(), CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
            scheduler.rescheduleJob(TriggerKey.triggerKey(cronJob.getJobName()), newTrigger);
            cronJob.setJobStatus(JobState.SCHEDULED);
            entityService.save(cronJob);
        } catch (SchedulerException e) {
            throw new ScheduleJobException(String.format("Failed to reschedule job %s", cronJob.getJobName()), e);
        }
    }

    @Override
    public CronJob getJob(final String jobName, final String jobGroup) {
        ServicesUtils.validateParameterNotNullStandardMessage("jobName", jobName);
        ServicesUtils.validateParameterNotNullStandardMessage("jobGroup", jobGroup);

        return cronJobRepository.findByJobNameAndJobGroup(jobName, jobGroup);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        scheduler.getListenerManager().addJobListener(jobStateListener);
    }
}
