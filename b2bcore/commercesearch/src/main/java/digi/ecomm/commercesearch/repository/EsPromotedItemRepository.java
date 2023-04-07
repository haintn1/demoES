package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.advance.EsAdvancedSearchConfig;
import digi.ecomm.entity.commercesearch.advance.EsPromotedItem;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;


@Repository("promotedItemRepository")
@RepositoryRestResource(path = EsPromotedItemRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsPromotedItemRepository.ITEM_RESOURCE_REL)
public interface EsPromotedItemRepository extends BaseRepository<EsPromotedItem, Long> {

    String PATH = "promoted-items";
    String ITEM_RESOURCE_REL = "promoted-item";

    /**
     * Find promoted items by the {@link EsAdvancedSearchConfig} it belongs to.
     *
     * @param advancedSearchConfig
     * @return list of {@link EsPromotedItem}s or empty list
     */
    List<EsPromotedItem> findByAdvancedSearchConfig(EsAdvancedSearchConfig advancedSearchConfig);
}
