package digi.ecomm.pcm.repository;

import digi.ecomm.entity.pcm.Attribute;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("attributeRepository")
@RepositoryRestResource(path = AttributeRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = AttributeRepository.ITEM_RESOURCE_REL)
public interface AttributeRepository extends BaseRepository<Attribute, Long> {

    String PATH = "attributes";
    String ITEM_RESOURCE_REL = "attribute";

    /**
     * Find product attribute by name.
     *
     * @param name
     * @return
     */
    Optional<Attribute> findByName(String name);
}
