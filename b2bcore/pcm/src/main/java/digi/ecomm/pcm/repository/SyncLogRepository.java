package digi.ecomm.pcm.repository;

import digi.ecomm.entity.sync.ActionType;
import digi.ecomm.entity.sync.SyncLog;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("syncLogRepository")
@RepositoryRestResource(path = SyncLogRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = SyncLogRepository.ITEM_RESOURCE_REL)
public interface SyncLogRepository extends BaseRepository<SyncLog, Long> {

    String PATH = "sync-logs";
    String ITEM_RESOURCE_REL = "sync-log";

    /**
     * Find synchronization log by action type.
     *
     * @param actionType
     * @return
     */
    List<SyncLog> findByActionType(ActionType actionType);

    /**
     * Remove all archived sync logs.
     *
     * @return number of removed records.
     */
    long removeByArchivedIsTrue();
}
