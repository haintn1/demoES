package digi.ecomm.pcm.service;

import digi.ecomm.entity.sync.ActionType;
import digi.ecomm.entity.sync.SyncLog;
import digi.ecomm.entity.sync.SynchronizableEntity;

import java.util.Collection;

public interface SyncLogService {

    /**
     * Create a sync log entry.
     *
     * @param entity
     * @param actionType
     * @param failedMessage
     * @param <T>
     * @return
     */
    <T extends SynchronizableEntity> SyncLog createLog(T entity, ActionType actionType, String failedMessage);

    /**
     * Create a sync log entry.
     *
     * @param entity
     * @param actionType
     * @param linked
     * @param failedMessage
     * @param <T>
     * @return
     */
    <T extends SynchronizableEntity> SyncLog createLog(T entity, T linked, ActionType actionType, String failedMessage);

    /**
     * Archive list of logs.
     *
     * @param logs
     */
    void archive(Collection<SyncLog> logs);

    /**
     * Remove all archived sync logs.
     *
     * @return number of removed records.
     */
    long deleteArchivedLogs();
}
