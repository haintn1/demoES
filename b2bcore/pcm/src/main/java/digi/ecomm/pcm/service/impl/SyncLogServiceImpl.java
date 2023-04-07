package digi.ecomm.pcm.service.impl;

import digi.ecomm.entity.sync.ActionType;
import digi.ecomm.entity.sync.SyncLog;
import digi.ecomm.entity.sync.SynchronizableEntity;
import digi.ecomm.pcm.repository.SyncLogRepository;
import digi.ecomm.pcm.service.SyncLogService;
import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class SyncLogServiceImpl implements SyncLogService {

    private final SyncLogRepository syncLogRepository;

    @Override
    public <T extends SynchronizableEntity> SyncLog createLog(final T entity, final ActionType actionType,
                                                              final String failedMessage) {
        ServicesUtils.validateParameterNotNullStandardMessage("entity", entity);
        ServicesUtils.validateParameterNotNullStandardMessage("actionType", actionType);

        if (!ActionType.CREATE.equals(actionType) && !ActionType.UPDATE.equals(actionType)
                && !ActionType.DELETE.equals(actionType)) {
            throw new IllegalArgumentException("Action type should be CREATE or UPDATE or DELETE");
        }

        final SyncLog log = createLogCommon(entity, actionType, failedMessage);
        return syncLogRepository.save(log);
    }

    @Override
    public <T extends SynchronizableEntity> SyncLog createLog(final T entity, final T linked,
                                                              final ActionType actionType, final String failedMessage) {
        ServicesUtils.validateParameterNotNullStandardMessage("entity", entity);
        ServicesUtils.validateParameterNotNullStandardMessage("linked", linked);
        ServicesUtils.validateParameterNotNullStandardMessage("actionType", actionType);

        if (!ActionType.LINKED.equals(actionType) && !ActionType.UNLINKED.equals(actionType)) {
            throw new IllegalArgumentException("Action type should be LINKED or UNLINKED");
        }

        final SyncLog log = createLogCommon(entity, actionType, failedMessage);
        log.setLinkedClass(linked.getClass().getName());
        log.setLinkedId(linked.getId());
        log.setLinkedExternalId(linked.getExternalId());
        return syncLogRepository.save(log);
    }

    private <T extends SynchronizableEntity> SyncLog createLogCommon(final T entity, final ActionType actionType,
                                                                     final String failedMessage) {
        final SyncLog log = new SyncLog();
        log.setTypeClass(entity.getClass().getName());
        log.setEntityId(entity.getId());
        log.setExternalId(entity.getExternalId());
        log.setActionType(actionType);
        log.setMessage(failedMessage);
        return log;
    }

    @Override
    public void archive(final Collection<SyncLog> logs) {
        ServicesUtils.validateParameterNotNullStandardMessage("logs", logs);

        logs.forEach(log -> log.setArchived(Boolean.TRUE));
        syncLogRepository.saveAll(logs);
    }

    @Override
    public long deleteArchivedLogs() {
        return syncLogRepository.removeByArchivedIsTrue();
    }
}
