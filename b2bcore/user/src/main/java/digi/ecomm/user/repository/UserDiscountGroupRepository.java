package digi.ecomm.user.repository;

import digi.ecomm.entity.user.UserDiscountGroup;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("userDiscountGroupRepository")
@RepositoryRestResource(path = UserDiscountGroupRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = UserDiscountGroupRepository.ITEM_RESOURCE_REL)
public interface UserDiscountGroupRepository extends BaseRepository<UserDiscountGroup, Long> {

    String PATH = "user-discount-groups";
    String ITEM_RESOURCE_REL = "user-discount-group";

}
