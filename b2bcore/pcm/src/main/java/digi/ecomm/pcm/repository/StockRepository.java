package digi.ecomm.pcm.repository;

import digi.ecomm.entity.pcm.Stock;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("stockRepository")
@RepositoryRestResource(path = StockRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = StockRepository.ITEM_RESOURCE_REL)
public interface StockRepository extends BaseRepository<Stock, Long> {

    String PATH = "stocks";
    String ITEM_RESOURCE_REL = "stock";

    /**
     * Find stocks attribute by product code.
     *
     * @param productCode
     * @return
     */
    List<Stock> findByProductCode(String productCode);
}
