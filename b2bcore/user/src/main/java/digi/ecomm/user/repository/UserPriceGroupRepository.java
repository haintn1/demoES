package digi.ecomm.user.repository;

import digi.ecomm.entity.user.UserPriceGroup;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("userPriceGroupRepository")
@RepositoryRestResource(path = UserPriceGroupRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = UserPriceGroupRepository.ITEM_RESOURCE_REL)
public interface UserPriceGroupRepository extends BaseRepository<UserPriceGroup, Long> {

    String PATH = "user-price-groups";
    String ITEM_RESOURCE_REL = "user-price-group";

}
