package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.EsServerConfig;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;

@Repository("serverConfigRepository")
@RepositoryRestResource(path = EsServerConfigRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsServerConfigRepository.ITEM_RESOURCE_REL)
public interface EsServerConfigRepository extends BaseRepository<EsServerConfig, Long> {

    String PATH = "server-configs";
    String ITEM_RESOURCE_REL = "server-config";

    /**
     * Find <code>EsServerConfig</code> by <code>name</code>.
     *
     * @param name
     * @return
     */
    EsServerConfig findByName(String name);
}
