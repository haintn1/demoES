package digi.ecomm.basecommerce.store.repository;

import digi.ecomm.entity.store.Warehouse;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("warehouseRepository")
@RepositoryRestResource(path = WarehouseRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = WarehouseRepository.ITEM_RESOURCE_REL)
public interface WarehouseRepository extends BaseRepository<Warehouse, Long> {

    String PATH = "warehouses";
    String ITEM_RESOURCE_REL = "warehouse";

}
