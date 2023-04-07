package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import digi.ecomm.entity.commercesearch.EsSearchConfig;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;

@Repository("searchConfigRepository")
@RepositoryRestResource(path = EsSearchConfigRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsSearchConfigRepository.ITEM_RESOURCE_REL)
public interface EsSearchConfigRepository extends BaseRepository<EsSearchConfig, Long> {

    String PATH = "search-configs";
    String ITEM_RESOURCE_REL = "search-config";

    /**
     * Find <code>EsSearchConfig</code> by <code>facetSearchConfig</code>.
     *
     * @param facetSearchConfig
     * @return
     */
    EsSearchConfig findByFacetSearchConfig(EsFacetSearchConfig facetSearchConfig);
}
