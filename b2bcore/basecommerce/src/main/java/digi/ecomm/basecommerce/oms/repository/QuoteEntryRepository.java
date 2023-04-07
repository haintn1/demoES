package digi.ecomm.basecommerce.oms.repository;

import digi.ecomm.entity.oms.Order;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("quoteEntryRepository")
@RepositoryRestResource(path = QuoteEntryRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = QuoteEntryRepository.ITEM_RESOURCE_REL)
public interface QuoteEntryRepository extends BaseRepository<Order, Long> {

    String PATH = "quote-entries";
    String ITEM_RESOURCE_REL = "quote-entry";

}
