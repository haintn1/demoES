package digi.ecomm.basecommerce.promotion.repository;

import digi.ecomm.entity.promotion.PromotionGroup;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("promotionGroupRepository")
@RepositoryRestResource(path = PromotionGroupRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = PromotionGroupRepository.ITEM_RESOURCE_REL)
public interface PromotionGroupRepository extends BaseRepository<PromotionGroup, Long> {

    String PATH = "promotion-groups";
    String ITEM_RESOURCE_REL = "promotion-group";

}
