package digi.ecomm.basecommerce.organization.repository;

import digi.ecomm.entity.organization.B2BCostCenter;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("b2bCostCenterRepository")
@RepositoryRestResource(path = B2BCostCenterRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = B2BCostCenterRepository.ITEM_RESOURCE_REL)
public interface B2BCostCenterRepository extends BaseRepository<B2BCostCenter, Long> {

    String PATH = "cost-centers";
    String ITEM_RESOURCE_REL = "cost-center";

}
