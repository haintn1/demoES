package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.EsIndex;
import digi.ecomm.entity.commercesearch.EsSort;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;

@Repository("sortRepository")
@RepositoryRestResource(path = EsSortRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsSortRepository.ITEM_RESOURCE_REL)
public interface EsSortRepository extends BaseRepository<EsSort, Long> {

    String PATH = "sorts";
    String ITEM_RESOURCE_REL = "sort";

    /**
     * Find a {@link EsSort} by its code and index.
     *
     * @param code
     * @param index
     * @return
     */
    EsSort findByCodeAndIndex(String code, EsIndex index);

    /**
     * Find a {@link EsSort} by its index.
     *
     * @param index
     * @return
     */
    List<EsSort> findByIndex(EsIndex index);
}
