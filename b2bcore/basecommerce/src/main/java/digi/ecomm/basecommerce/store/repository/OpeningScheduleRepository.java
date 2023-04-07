package digi.ecomm.basecommerce.store.repository;

import digi.ecomm.entity.store.OpeningSchedule;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("openingScheduleRepository")
@RepositoryRestResource(path = OpeningScheduleRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = OpeningScheduleRepository.ITEM_RESOURCE_REL)
public interface OpeningScheduleRepository extends BaseRepository<OpeningSchedule, Long> {

    String PATH = "opening-schedules";
    String ITEM_RESOURCE_REL = "opening-schedule";

}
