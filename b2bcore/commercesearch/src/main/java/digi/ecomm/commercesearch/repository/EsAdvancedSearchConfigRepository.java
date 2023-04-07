package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import digi.ecomm.entity.commercesearch.advance.EsAdvancedSearchConfig;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("advancedSearchConfigRepository")
@RepositoryRestResource(path = EsAdvancedSearchConfigRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsAdvancedSearchConfigRepository.ITEM_RESOURCE_REL)
public interface EsAdvancedSearchConfigRepository extends BaseRepository<EsAdvancedSearchConfig, Long> {

    String PATH = "advanced-search-configs";
    String ITEM_RESOURCE_REL = "advanced-search-config";

    /**
     * Find {@link EsAdvancedSearchConfig} by {@link EsFacetSearchConfig}.
     *
     * @param facetSearchConfig
     * @return {@link EsAdvancedSearchConfig} or null
     */
    EsAdvancedSearchConfig findByFacetSearchConfig(EsFacetSearchConfig facetSearchConfig);
}
