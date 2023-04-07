package digi.ecomm.basecommerce.promotion.repository;

import digi.ecomm.entity.promotion.AbstractPromotion;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("promotionRepository")
@RepositoryRestResource(path = PromotionRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = PromotionRepository.ITEM_RESOURCE_REL)
public interface PromotionRepository extends BaseRepository<AbstractPromotion, Long> {

    String PATH = "promotions";
    String ITEM_RESOURCE_REL = "promotion";

}
