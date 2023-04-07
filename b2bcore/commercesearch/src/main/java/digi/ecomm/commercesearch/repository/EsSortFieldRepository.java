package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.EsSort;
import digi.ecomm.entity.commercesearch.EsSortField;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;

@Repository("sortFieldRepository")
@RepositoryRestResource(path = EsSortFieldRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsSortFieldRepository.ITEM_RESOURCE_REL)
public interface EsSortFieldRepository extends BaseRepository<EsSortField, Long> {

    String PATH = "sort-fields";
    String ITEM_RESOURCE_REL = "sort-field";

    /**
     * Find <code>EsSortField</code> by <code>fieldName</code> and <code>asc</code>.
     *
     * @param fieldName
     * @return
     */
    EsSortField findByFieldNameAndAscending(String fieldName, Boolean asc);

    /**
     * Find <code>EsSortField</code> by <code>EsSort</code>.
     *
     * @param sort
     * @return
     */
    List<EsSortField> findBySort(EsSort sort);
}
