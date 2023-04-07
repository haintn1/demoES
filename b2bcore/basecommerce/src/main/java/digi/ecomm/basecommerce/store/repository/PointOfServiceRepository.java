package digi.ecomm.basecommerce.store.repository;

import digi.ecomm.entity.store.PointOfService;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("posRepository")
@RepositoryRestResource(path = PointOfServiceRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = PointOfServiceRepository.ITEM_RESOURCE_REL)
public interface PointOfServiceRepository extends BaseRepository<PointOfService, Long> {

    String PATH = "point-of-services";
    String ITEM_RESOURCE_REL = "point-of-service";

}
