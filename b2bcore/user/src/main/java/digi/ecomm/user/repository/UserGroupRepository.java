package digi.ecomm.user.repository;

import digi.ecomm.entity.user.UserGroup;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("userGroupRepository")
@RepositoryRestResource(path = UserGroupRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = UserGroupRepository.ITEM_RESOURCE_REL)
public interface UserGroupRepository extends BaseRepository<UserGroup, Long> {

    String PATH = "user-groups";
    String ITEM_RESOURCE_REL = "user-group";

}
