package digi.ecomm.basecommerce.oms.repository;

import digi.ecomm.entity.oms.Cart;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("cartRepository")
@RepositoryRestResource(path = CartRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = CartRepository.ITEM_RESOURCE_REL)
public interface CartRepository extends BaseRepository<Cart, Long> {

    String PATH = "carts";
    String ITEM_RESOURCE_REL = "cart";

}
