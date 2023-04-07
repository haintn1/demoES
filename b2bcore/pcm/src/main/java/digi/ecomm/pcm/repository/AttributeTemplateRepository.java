package digi.ecomm.pcm.repository;

import digi.ecomm.entity.pcm.AttributeTemplate;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("attributeTemplateRepository")
@RepositoryRestResource(path = AttributeTemplateRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = AttributeTemplateRepository.ITEM_RESOURCE_REL)
public interface AttributeTemplateRepository extends BaseRepository<AttributeTemplate, Long> {

    String PATH = "attribute-templates";
    String ITEM_RESOURCE_REL = "attribute-template";

    /**
     * Find product attribute template by name.
     *
     * @param name
     * @return
     */
    Optional<AttributeTemplate> findByName(String name);
}
