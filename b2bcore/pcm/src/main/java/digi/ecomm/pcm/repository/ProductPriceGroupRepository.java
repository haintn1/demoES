package digi.ecomm.pcm.repository;

import digi.ecomm.platformservice.enumeration.EnumEntityRepository;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("productPriceGroupRepository")
@RepositoryRestResource(path = ProductPriceGroupRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = ProductPriceGroupRepository.ITEM_RESOURCE_REL)
public interface ProductPriceGroupRepository extends EnumEntityRepository {

    String PATH = "product-price-groups";
    String ITEM_RESOURCE_REL = "product-price-group";
}
