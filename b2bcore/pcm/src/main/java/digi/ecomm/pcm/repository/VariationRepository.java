package digi.ecomm.pcm.repository;

import digi.ecomm.entity.pcm.Variation;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository("variationRepository")
@RepositoryRestResource(path = VariationRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = VariationRepository.ITEM_RESOURCE_REL)
public interface VariationRepository extends BaseRepository<Variation, Long> {

    String PATH = "variations";
    String ITEM_RESOURCE_REL = "variation";

    /**
     * Find variation by code.
     *
     * @param code
     * @return
     */
    Optional<Variation> findByCode(String code);

    /**
     * Find variations by external ids.
     *
     * @return
     */
    @Query("SELECT v FROM Variation v WHERE v.sync.externalId IN (:externalIds)")
    List<Variation> findByExternalIds(@Param("externalIds") Collection<String> externalIds);
}
