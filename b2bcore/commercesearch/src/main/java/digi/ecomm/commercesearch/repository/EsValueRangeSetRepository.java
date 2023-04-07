package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.EsValueRangeSet;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;

@Repository("valueRangeSetRepository")
@RepositoryRestResource(path = EsValueRangeSetRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsValueRangeSetRepository.ITEM_RESOURCE_REL)
public interface EsValueRangeSetRepository extends BaseRepository<EsValueRangeSet, Long> {

    String PATH = "value-range-sets";
    String ITEM_RESOURCE_REL = "value-range-set";

    /**
     * Find <code>EsValueRangeSet</code> by <code>name</code>.
     *
     * @param name
     * @return
     */
    EsValueRangeSet findByName(String name);
}