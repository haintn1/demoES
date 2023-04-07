package digi.ecomm.basecommerce.oms.repository;

import digi.ecomm.entity.oms.Cart;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("quoteRepository")
@RepositoryRestResource(path = QuoteRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = QuoteRepository.ITEM_RESOURCE_REL)
public interface QuoteRepository extends BaseRepository<Cart, Long> {

    String PATH = "quotes";
    String ITEM_RESOURCE_REL = "quote";

}
