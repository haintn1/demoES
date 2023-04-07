package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.EsIndex;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;

@Repository("indexedPropertyRepository")
@RepositoryRestResource(path = EsIndexedPropertyRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsIndexedPropertyRepository.ITEM_RESOURCE_REL)
public interface EsIndexedPropertyRepository extends BaseRepository<EsIndexedProperty, Long> {

    String PATH = "indexed-properties";
    String ITEM_RESOURCE_REL = "indexed-property";

    /**
     * Find list of indexed properties by its name the index they belong to.
     *
     * @param name
     * @param index
     * @return EsIndexedProperty or null
     */
    EsIndexedProperty findByNameAndIndex(String name, EsIndex index);

    /**
     * Find list of indexed properties by the index they belong to.
     *
     * @param index
     * @return list of EsIndexedProperty or empty list
     */
    List<EsIndexedProperty> findByIndex(EsIndex index);
}
