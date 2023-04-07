package digi.ecomm.platformservice.cronjob.service;

import digi.ecomm.entity.cronjob.CronJob;
import org.quartz.SchedulerException;
import org.quartz.SchedulerMetaData;

public interface CronJobService {

    /**
     * Get {@link SchedulerMetaData}.
     *
     * @return
     * @throws SchedulerException
     */
    SchedulerMetaData getMetaData() throws SchedulerException;

    /**
     * Delete a job.
     *
     * @param jobId
     * @return true if success, false if failed
     */
    boolean deleteJob(Long jobId);

    /**
     * Suggest stop a job's running execution immediately and pause the future executions until explicitly call {@link #resumeJob(Long)}.
     *
     * @param jobId
     * @return true if success, false if failed
     */
    boolean pauseJob(Long jobId);

    /**
     * Resume a job.
     *
     * @param jobId
     * @return true if success, false if failed
     */
    boolean resumeJob(Long jobId);

    /**
     * Start a job immediately.
     *
     * @param jobId
     * @return true if success, false if failed
     */
    boolean startJobNow(Long jobId);

    /**
     * @param schedulerJobInfo
     * @return
     */
    void saveJob(CronJob schedulerJobInfo);

    /**
     * Get {@link CronJob} identified by its name and group.
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    CronJob getJob(String jobName, String jobGroup);

    /**
     * Suggest stop a job's running execution immediately. But it has still been scheduled and will be executed
     * when the trigger gets fired next time.
     *
     * @param jobId
     * @return
     */
    boolean stopJob(Long jobId);
}
