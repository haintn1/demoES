package digi.ecomm.pcm.sync.push.handler;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.sync.ActionType;
import digi.ecomm.entity.sync.SynchronizableEntity;
import digi.ecomm.pcm.service.SyncLogService;
import digi.ecomm.pcm.sync.push.exception.FailedDataCreationException;
import digi.ecomm.pcm.sync.push.exception.FailedDataDeletionException;
import digi.ecomm.pcm.sync.push.PushResult;
import digi.ecomm.platformservice.persistent.service.EntityService;

import javax.annotation.Resource;

public abstract class AbstractEventHandler<T extends SynchronizableEntity> {
    @Resource
    private SyncLogService syncLogService;

    @Resource
    private EntityService entityService;

    /**
     * Post create process.
     *
     * @param entity
     * @param result
     */
    protected void postCreate(final T entity, final PushResult result) {
        if (result.isSuccess()) {
            entity.setExternalId(result.getExternalId());
            entityService.save((AbstractEntity) entity);
        } else {
            syncLogService.createLog(entity, ActionType.CREATE, result.getFailedMessage());
            throw new FailedDataCreationException(result.getFailedMessage());
        }
    }

    /**
     * Post update process.
     *
     * @param entity
     * @param result
     */
    protected void postUpdate(final T entity, final PushResult result) {
        if (!result.isSuccess()) {
            syncLogService.createLog(entity, ActionType.UPDATE, result.getFailedMessage());
            throw new FailedDataCreationException(result.getFailedMessage());
        }
    }

    /**
     * Post delete process.
     *
     * @param entity
     * @param result
     */
    protected void postDelete(final T entity, final PushResult result) {
        if (!result.isSuccess()) {
            syncLogService.createLog(entity, ActionType.DELETE, result.getFailedMessage());
            throw new FailedDataDeletionException(result.getFailedMessage());
        }
    }
}
