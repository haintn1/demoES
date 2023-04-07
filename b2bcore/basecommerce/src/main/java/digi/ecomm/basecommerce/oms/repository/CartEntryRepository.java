package digi.ecomm.basecommerce.oms.repository;

import digi.ecomm.entity.oms.CartEntry;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("cartEntryRepository")
@RepositoryRestResource(path = CartEntryRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = CartEntryRepository.ITEM_RESOURCE_REL)
public interface CartEntryRepository extends BaseRepository<CartEntry, Long> {

    String PATH = "cart-entries";
    String ITEM_RESOURCE_REL = "cart-entry";

}
