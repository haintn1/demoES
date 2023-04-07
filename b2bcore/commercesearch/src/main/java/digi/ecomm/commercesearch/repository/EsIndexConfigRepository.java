package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.EsIndexConfig;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;

@Repository("indexConfigRepository")
@RepositoryRestResource(path = EsIndexConfigRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsIndexConfigRepository.ITEM_RESOURCE_REL)
public interface EsIndexConfigRepository extends BaseRepository<EsIndexConfig, Long> {

    String PATH = "index-configs";
    String ITEM_RESOURCE_REL = "index-config";

    /**
     * Find <code>EsIndexConfig</code> by <code>name</code>.
     *
     * @param name
     * @return
     */
    EsIndexConfig findByName(String name);
}
