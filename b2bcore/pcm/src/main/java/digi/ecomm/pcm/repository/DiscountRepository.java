package digi.ecomm.pcm.repository;

import digi.ecomm.entity.pcm.Discount;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("discountRepository")
@RepositoryRestResource(path = DiscountRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = DiscountRepository.ITEM_RESOURCE_REL)
public interface DiscountRepository extends BaseRepository<Discount, Long> {

    String PATH = "discounts";
    String ITEM_RESOURCE_REL = "discount";

}
