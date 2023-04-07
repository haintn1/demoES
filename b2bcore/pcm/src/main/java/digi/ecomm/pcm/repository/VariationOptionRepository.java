package digi.ecomm.pcm.repository;

import digi.ecomm.entity.pcm.Variation;
import digi.ecomm.entity.pcm.VariationOption;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository("variationOptionRepository")
@RepositoryRestResource(path = VariationOptionRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = VariationOptionRepository.ITEM_RESOURCE_REL)
public interface VariationOptionRepository extends BaseRepository<VariationOption, Long> {

    String PATH = "variation-options";
    String ITEM_RESOURCE_REL = "variation-option";

    /**
     * Find variation options by external ids.
     *
     * @return
     */
    @Query("SELECT o FROM VariationOption o WHERE o.sync.externalId IN (:externalIds)")
    List<VariationOption> findByExternalIds(@Param("externalIds") Collection<String> externalIds);

    /**
     * Find variation option by code.
     *
     * @param code
     * @return
     */
    Optional<VariationOption> findByCode(String code);

    /**
     * Find variation options by variation.
     *
     * @param variation
     * @return
     */
    List<VariationOption> findByVariation(Variation variation);
}
