package digi.ecomm.basecommerce.organization.repository;

import digi.ecomm.entity.organization.B2BPermission;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("b2bPermissionRepository")
@RepositoryRestResource(path = B2BPermissionRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = B2BPermissionRepository.ITEM_RESOURCE_REL)
public interface B2BPermissionRepository extends BaseRepository<B2BPermission, Long> {

    String PATH = "b2b-permissions";
    String ITEM_RESOURCE_REL = "b2b-permission";

}
