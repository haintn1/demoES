package digi.ecomm.basecommerce.oms.repository;

import digi.ecomm.entity.oms.Order;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("orderEntryRepository")
@RepositoryRestResource(path = OrderEntryRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = OrderEntryRepository.ITEM_RESOURCE_REL)
public interface OrderEntryRepository extends BaseRepository<Order, Long> {

    String PATH = "order-entries";
    String ITEM_RESOURCE_REL = "order-entry";

}
