package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import digi.ecomm.entity.commercesearch.EsSynonymConfig;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;

@Repository("synonymConfigRepository")
@RepositoryRestResource(path = EsSynonymConfigRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsSynonymConfigRepository.ITEM_RESOURCE_REL)
public interface EsSynonymConfigRepository extends BaseRepository<EsSynonymConfig, Long> {

    String PATH = "synonyms";
    String ITEM_RESOURCE_REL = "synonym";

    /**
     * Find list of synonyms by the facetSearchConfig they belong to.
     *
     * @param facetSearchConfig
     * @return list of EsSynonymConfig or empty list
     */
    List<EsSynonymConfig> findByFacetSearchConfig(EsFacetSearchConfig facetSearchConfig);
}
