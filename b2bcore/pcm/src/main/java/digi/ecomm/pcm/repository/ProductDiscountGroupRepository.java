package digi.ecomm.pcm.repository;

import digi.ecomm.platformservice.enumeration.EnumEntityRepository;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("productDiscountGroupRepository")
@RepositoryRestResource(path = ProductDiscountGroupRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = ProductDiscountGroupRepository.ITEM_RESOURCE_REL)
public interface ProductDiscountGroupRepository extends EnumEntityRepository {

    String PATH = "product-discount-groups";
    String ITEM_RESOURCE_REL = "product-discount-group";
}
