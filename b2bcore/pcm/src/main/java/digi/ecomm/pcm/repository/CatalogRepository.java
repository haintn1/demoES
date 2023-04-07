package digi.ecomm.pcm.repository;

import digi.ecomm.entity.catalog.Catalog;
import digi.ecomm.entity.pcm.CatalogVersion;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("catalogRepository")
@RepositoryRestResource(path = CatalogRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = CatalogRepository.ITEM_RESOURCE_REL)
public interface CatalogRepository extends BaseRepository<Catalog, Long> {

    String PATH = "catalogs";
    String ITEM_RESOURCE_REL = "catalog";

    /**
     * Find catalog by code and version.
     *
     * @param code
     * @param catalogVersion
     * @return
     */
    Optional<Catalog> findByCodeAndVersion(String code, CatalogVersion catalogVersion);
}
