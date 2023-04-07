package digi.ecomm.pcm.repository;

import digi.ecomm.entity.pcm.PriceRow;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("priceRowRepository")
@RepositoryRestResource(path = PriceRowRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = PriceRowRepository.ITEM_RESOURCE_REL)
public interface PriceRowRepository extends BaseRepository<PriceRow, Long> {

    String PATH = "price-rows";
    String ITEM_RESOURCE_REL = "price-row";

}
