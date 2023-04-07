package digi.ecomm.pcm.repository;

import digi.ecomm.entity.pcm.Product;
import digi.ecomm.entity.pcm.ProductFeature;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productFeatureRepository")
@RepositoryRestResource(path = ProductFeatureRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = ProductFeatureRepository.ITEM_RESOURCE_REL)
public interface ProductFeatureRepository extends BaseRepository<ProductFeature, Long> {

    String PATH = "product-features";
    String ITEM_RESOURCE_REL = "product-feature";

    /**
     * Find product features by product.
     *
     * @param product
     * @return
     */
    List<ProductFeature> findByProduct(Product product);
}
