package digi.ecomm.basecommerce.oms.repository;

import digi.ecomm.entity.oms.Order;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("orderRepository")
@RepositoryRestResource(path = OrderRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = OrderRepository.ITEM_RESOURCE_REL)
public interface OrderRepository extends BaseRepository<Order, Long> {

    String PATH = "orders";
    String ITEM_RESOURCE_REL = "order";

}
