package digi.ecomm.basecommerce.store.repository;

import digi.ecomm.entity.store.BaseStore;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("baseStoreRepository")
@RepositoryRestResource(path = BaseStoreRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = BaseStoreRepository.ITEM_RESOURCE_REL)
public interface BaseStoreRepository extends BaseRepository<BaseStore, Long> {

    String PATH = "base-stores";
    String ITEM_RESOURCE_REL = "base-store";

}
