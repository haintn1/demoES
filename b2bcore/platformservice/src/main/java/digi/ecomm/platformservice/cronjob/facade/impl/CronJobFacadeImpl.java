package digi.ecomm.platformservice.cronjob.facade.impl;

import digi.ecomm.platformservice.cronjob.facade.CronJobFacade;
import digi.ecomm.datatransferobject.EmptyResponse;
import digi.ecomm.platformservice.cronjob.service.CronJobService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CronJobFacadeImpl implements CronJobFacade {

    private final CronJobService cronJobService;

    @Override
    public EmptyResponse pauseJob(final Long jobId) {
        cronJobService.pauseJob(jobId);
        return new EmptyResponse();
    }

    @Override
    public EmptyResponse resumeJob(final Long jobId) {
        cronJobService.resumeJob(jobId);
        return new EmptyResponse();
    }

    @Override
    public EmptyResponse stopJob(final Long jobId) {
        cronJobService.stopJob(jobId);
        return new EmptyResponse();
    }

    @Override
    public EmptyResponse startJobNow(final Long jobId) {
        cronJobService.startJobNow(jobId);
        return new EmptyResponse();
    }
}
