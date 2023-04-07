package digi.ecomm.pcm.repository;

import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("variantProductRepository")
@RepositoryRestResource(path = VariantProductRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = VariantProductRepository.ITEM_RESOURCE_REL)
public interface VariantProductRepository extends ProductRepository {

    String PATH = "variant-products";
    String ITEM_RESOURCE_REL = "variant-product";
}
