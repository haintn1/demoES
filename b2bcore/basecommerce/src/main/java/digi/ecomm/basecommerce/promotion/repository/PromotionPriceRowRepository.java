package digi.ecomm.basecommerce.promotion.repository;

import digi.ecomm.entity.promotion.PromotionPriceRow;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("promotionPriceRepository")
@RepositoryRestResource(path = PromotionPriceRowRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = PromotionPriceRowRepository.ITEM_RESOURCE_REL)
public interface PromotionPriceRowRepository extends BaseRepository<PromotionPriceRow, Long> {

    String PATH = "promotion-prices";
    String ITEM_RESOURCE_REL = "promotion-price";

}
