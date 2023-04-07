package digi.ecomm.basecommerce.organization.repository;

import digi.ecomm.entity.organization.B2BUnit;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("b2bUnitRepository")
@RepositoryRestResource(path = B2BUnitRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = B2BUnitRepository.ITEM_RESOURCE_REL)
public interface B2BUnitRepository extends BaseRepository<B2BUnit, Long> {

    String PATH = "b2b-units";
    String ITEM_RESOURCE_REL = "b2b-unit";

}
