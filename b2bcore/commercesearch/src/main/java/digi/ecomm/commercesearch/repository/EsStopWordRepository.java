package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import digi.ecomm.entity.commercesearch.EsStopWord;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;

@Repository("stopWordRepository")
@RepositoryRestResource(path = EsStopWordRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsStopWordRepository.ITEM_RESOURCE_REL)
public interface EsStopWordRepository extends BaseRepository<EsStopWord, Long> {

    String PATH = "stop-words";
    String ITEM_RESOURCE_REL = "stop-word";

    /**
     * Find list of stop words by the facetSearchConfig they belong to.
     *
     * @param facetSearchConfig
     * @return list of EsStopWord or empty list
     */
    List<EsStopWord> findByFacetSearchConfig(EsFacetSearchConfig facetSearchConfig);
}
