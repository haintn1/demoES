package digi.ecomm.pcm.repository;

import digi.ecomm.entity.catalog.Catalog;
import digi.ecomm.entity.pcm.Category;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("categoryRepository")
@RepositoryRestResource(path = CategoryRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = CategoryRepository.ITEM_RESOURCE_REL)
public interface CategoryRepository extends BaseRepository<Category, Long> {

    String PATH = "categories";
    String ITEM_RESOURCE_REL = "category";

    /**
     * Find category by code and catalog.
     *
     * @param code
     * @param catalog
     * @return
     */
    Optional<Category> findByCodeAndCatalog(String code, Catalog catalog);

    /**
     * Find categories by catalog.
     *
     * @param catalog
     * @return
     */
    List<Category> findByCatalog(Catalog catalog);
}
