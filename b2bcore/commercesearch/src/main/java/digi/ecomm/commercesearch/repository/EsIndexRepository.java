package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import digi.ecomm.entity.commercesearch.EsIndex;
import digi.ecomm.entity.commercesearch.EsIndexedEntityType;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;

@Repository("indexRepository")
@RepositoryRestResource(path = EsIndexRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsIndexRepository.ITEM_RESOURCE_REL)
public interface EsIndexRepository extends BaseRepository<EsIndex, Long> {

    String PATH = "indices";
    String ITEM_RESOURCE_REL = "index";

    /**
     * Find {@link EsIndex} by its name.
     *
     * @param name
     * @return
     */
    EsIndex findByName(String name);

    /**
     * Find {@link EsIndex} by its associated facet search config and the entity type it handle.
     *
     * @param facetSearchConfig
     * @param indexedEntityType
     * @return the {@link EsIndex} or null if not found
     */
    EsIndex findByFacetSearchConfigAndIndexedEntityType(EsFacetSearchConfig facetSearchConfig, EsIndexedEntityType indexedEntityType);
}
