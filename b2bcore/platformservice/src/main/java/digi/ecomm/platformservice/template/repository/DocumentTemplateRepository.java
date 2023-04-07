package digi.ecomm.platformservice.template.repository;

import digi.ecomm.entity.template.DocumentTemplate;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("documentTemplateRepository")
@RepositoryRestResource(path = DocumentTemplateRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = DocumentTemplateRepository.ITEM_RESOURCE_REL)
public interface DocumentTemplateRepository extends BaseRepository<DocumentTemplate, Long> {

    String PATH = "document-templates";
    String ITEM_RESOURCE_REL = "document-template";

}
