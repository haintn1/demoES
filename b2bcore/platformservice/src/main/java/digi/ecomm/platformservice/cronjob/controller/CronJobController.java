package digi.ecomm.platformservice.cronjob.controller;

import digi.ecomm.platformservice.cronjob.facade.CronJobFacade;
import digi.ecomm.datatransferobject.AbstractResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cronjobs")
public class CronJobController {

    @Resource(name = "cronJobFacade")
    private CronJobFacade cronJobFacade;

    /**
     * Pause a job.
     *
     * @param jobId
     * @return
     */
    @PutMapping("/{jobId}/pause")
    public AbstractResponse pauseJob(@PathVariable("jobId") final Long jobId) {
        return cronJobFacade.pauseJob(jobId);
    }

    /**
     * Resume a job.
     *
     * @param jobId
     * @return
     */
    @PutMapping("/{jobId}/resume")
    public AbstractResponse resumeJob(@PathVariable("jobId") final Long jobId) {
        return cronJobFacade.resumeJob(jobId);
    }

    /**
     * Stop a job.
     *
     * @param jobId
     * @return
     */
    @PutMapping("/{jobId}/stop")
    public AbstractResponse stopJob(@PathVariable("jobId") final Long jobId) {
        return cronJobFacade.stopJob(jobId);
    }

    /**
     * Start a job immediately.
     *
     * @param jobId
     * @return
     */
    @PutMapping("/{jobId}/start")
    public AbstractResponse startJob(@PathVariable("jobId") final Long jobId) {
        return cronJobFacade.startJobNow(jobId);
    }
}
