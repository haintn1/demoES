package digi.ecomm.platformservice.media.repository;

import digi.ecomm.entity.catalog.Catalog;
import digi.ecomm.entity.media.Media;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository("mediaRepository")
@RepositoryRestResource(path = MediaRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = MediaRepository.ITEM_RESOURCE_REL)
public interface MediaRepository extends BaseRepository<Media, Long> {

    String PATH = "medias";
    String ITEM_RESOURCE_REL = "media";

    /**
     * Find media by code and catalog.
     *
     * @param code
     * @param catalog
     * @return
     */
    Media findByCodeAndCatalog(String code, Catalog catalog);

    /**
     * Find medias by external ids.
     *
     * @return
     */
    @Query("SELECT m FROM Media m WHERE m.sync.externalId IN (:externalIds)")
    List<Media> findByExternalIds(@Param("externalIds") Collection<String> externalIds);

    /**
     * Find media by external id.
     *
     * @param externalId
     * @return
     */
    @Query("SELECT m FROM Media m WHERE m.sync.externalId = :externalId")
    Optional<Media> findByExternalId(@Param("externalId") String externalId);
}
