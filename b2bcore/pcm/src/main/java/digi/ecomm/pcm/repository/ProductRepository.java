package digi.ecomm.pcm.repository;

import digi.ecomm.entity.catalog.Catalog;
import digi.ecomm.entity.pcm.Product;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository("productRepository")
@RepositoryRestResource(path = ProductRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = ProductRepository.ITEM_RESOURCE_REL)
public interface ProductRepository extends BaseRepository<Product, Long> {

    String PATH = "products";
    String ITEM_RESOURCE_REL = "product";

    /**
     * Find product by code and catalog.
     *
     * @param code
     * @param catalog
     * @return
     */
    Optional<Product> findByCodeAndCatalog(String code, Catalog catalog);

    /**
     * Find products by external ids.
     *
     * @return
     */
    @Query("SELECT p FROM Product p WHERE p.sync.externalId IN (:externalIds)")
    List<Product> findByExternalIds(@Param("externalIds") Collection<String> externalIds);
}
