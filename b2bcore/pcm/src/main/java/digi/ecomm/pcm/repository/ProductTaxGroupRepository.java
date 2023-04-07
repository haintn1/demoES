package digi.ecomm.pcm.repository;

import digi.ecomm.platformservice.enumeration.EnumEntityRepository;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("productTaxGroupRepository")
@RepositoryRestResource(path = ProductTaxGroupRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = ProductTaxGroupRepository.ITEM_RESOURCE_REL)
public interface ProductTaxGroupRepository extends EnumEntityRepository {

    String PATH = "product-tax-groups";
    String ITEM_RESOURCE_REL = "product-tax-group";
}
