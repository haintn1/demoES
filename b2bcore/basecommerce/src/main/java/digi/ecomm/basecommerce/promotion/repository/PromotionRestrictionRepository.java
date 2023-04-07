package digi.ecomm.basecommerce.promotion.repository;

import digi.ecomm.entity.promotion.restriction.AbstractPromotionRestriction;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("promotionRestrictionRepository")
@RepositoryRestResource(path = PromotionRestrictionRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = PromotionRestrictionRepository.ITEM_RESOURCE_REL)
public interface PromotionRestrictionRepository extends BaseRepository<AbstractPromotionRestriction, Long> {

    String PATH = "promotion-restrictions";
    String ITEM_RESOURCE_REL = "promotion-restriction";

}
