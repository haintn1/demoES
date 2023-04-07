package digi.ecomm.pcm.repository;

import digi.ecomm.entity.pcm.Tax;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("taxRepository")
@RepositoryRestResource(path = TaxRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = TaxRepository.ITEM_RESOURCE_REL)
public interface TaxRepository extends BaseRepository<Tax, Long> {

    String PATH = "taxes";
    String ITEM_RESOURCE_REL = "tax";

}
