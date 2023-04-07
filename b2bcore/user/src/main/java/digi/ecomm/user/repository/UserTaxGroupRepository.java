package digi.ecomm.user.repository;

import digi.ecomm.entity.user.UserTaxGroup;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("userTaxGroupRepository")
@RepositoryRestResource(path = UserTaxGroupRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = UserTaxGroupRepository.ITEM_RESOURCE_REL)
public interface UserTaxGroupRepository extends BaseRepository<UserTaxGroup, Long> {

    String PATH = "user-tax-groups";
    String ITEM_RESOURCE_REL = "user-tax-group";

}
