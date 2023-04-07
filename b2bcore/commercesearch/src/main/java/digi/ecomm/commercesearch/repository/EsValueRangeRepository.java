package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.EsValueRange;
import digi.ecomm.entity.commercesearch.EsValueRangeSet;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;


@Repository("valueRangeRepository")
@RepositoryRestResource(path = EsValueRangeRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsValueRangeRepository.ITEM_RESOURCE_REL)
public interface EsValueRangeRepository extends BaseRepository<EsValueRange, Long> {

    String PATH = "value-ranges";
    String ITEM_RESOURCE_REL = "value-range";

    /**
     * Find a {@link EsValueRange} by its <code>name</code> and <code>valueRangeSet</code>.
     *
     * @param name
     * @param valueRangeSet
     * @return
     */
    EsValueRange findByNameAndValueRangeSet(String name, EsValueRangeSet valueRangeSet);

    /**
     * Find a {@link EsValueRange} by its esValueRangeSet.
     *
     * @param valueRangeSet
     * @return
     */
    List<EsValueRange> findByValueRangeSet(EsValueRangeSet valueRangeSet);
}
