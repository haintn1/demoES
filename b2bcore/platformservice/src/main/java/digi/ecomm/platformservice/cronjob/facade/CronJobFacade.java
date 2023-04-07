package digi.ecomm.platformservice.cronjob.facade;

import digi.ecomm.datatransferobject.EmptyResponse;

public interface CronJobFacade {

    /**
     * Pause a job.
     *
     * @param jobId
     * @return true if success, false if failed
     */
    EmptyResponse pauseJob(Long jobId);

    /**
     * Resume a job.
     *
     * @param jobId
     * @return true if success, false if failed
     */
    EmptyResponse resumeJob(Long jobId);

    /**
     * Start a job immediately.
     *
     * @param jobId
     * @return true if success, false if failed
     */
    EmptyResponse startJobNow(Long jobId);

    /**
     * Start a job immediately.
     *
     * @param jobId
     * @return
     */
    EmptyResponse stopJob(Long jobId);
}
