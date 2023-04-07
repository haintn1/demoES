package digi.ecomm.pcm.repository;

import digi.ecomm.entity.pcm.TaxRow;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("taxRowRepository")
@RepositoryRestResource(path = TaxRowRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = TaxRowRepository.ITEM_RESOURCE_REL)
public interface TaxRowRepository extends BaseRepository<TaxRow, Long> {

    String PATH = "tax-rows";
    String ITEM_RESOURCE_REL = "tax-row";

}
