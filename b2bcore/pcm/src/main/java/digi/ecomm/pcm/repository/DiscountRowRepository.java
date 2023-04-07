package digi.ecomm.pcm.repository;

import digi.ecomm.entity.pcm.DiscountRow;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("discountRowRepository")
@RepositoryRestResource(path = DiscountRowRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = DiscountRowRepository.ITEM_RESOURCE_REL)
public interface DiscountRowRepository extends BaseRepository<DiscountRow, Long> {

    String PATH = "discount-rows";
    String ITEM_RESOURCE_REL = "discount-row";

}
