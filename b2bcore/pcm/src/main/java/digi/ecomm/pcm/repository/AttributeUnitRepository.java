package digi.ecomm.pcm.repository;

import digi.ecomm.entity.pcm.AttributeUnit;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("attributeUnitRepository")
@RepositoryRestResource(path = AttributeUnitRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = AttributeUnitRepository.ITEM_RESOURCE_REL)
public interface AttributeUnitRepository extends BaseRepository<AttributeUnit, Long> {

    String PATH = "attribute-units";
    String ITEM_RESOURCE_REL = "attribute-unit";

    /**
     * Find product attribute unit by code.
     *
     * @param code
     * @return
     */
    Optional<AttributeUnit> findByCode(String code);
}
