package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;

@Repository("facetSearchConfigRepository")
@RepositoryRestResource(path = EsFacetSearchConfigRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsFacetSearchConfigRepository.ITEM_RESOURCE_REL)
public interface EsFacetSearchConfigRepository extends BaseRepository<EsFacetSearchConfig, Long> {

    String PATH = "facet-search-configs";
    String ITEM_RESOURCE_REL = "facet-search-config";

    /**
     * Find search config by its name.
     *
     * @param name
     * @return EsFacetSearchConfig or null if not found
     */
    EsFacetSearchConfig findByName(String name);
}
