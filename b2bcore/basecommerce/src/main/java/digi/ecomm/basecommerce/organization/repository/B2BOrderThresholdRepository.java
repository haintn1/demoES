package digi.ecomm.basecommerce.organization.repository;

import digi.ecomm.entity.organization.B2BOrderThresholdPermission;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("b2bOrderThresholdRepository")
@RepositoryRestResource(path = B2BOrderThresholdRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = B2BOrderThresholdRepository.ITEM_RESOURCE_REL)
public interface B2BOrderThresholdRepository extends BaseRepository<B2BOrderThresholdPermission, Long> {

    String PATH = "order-thresholds";
    String ITEM_RESOURCE_REL = "order-threshold";

}
