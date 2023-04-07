package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.EsServer;
import digi.ecomm.entity.commercesearch.EsServerConfig;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;

@Repository("serverRepository")
@RepositoryRestResource(path = EsServerRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsServerRepository.ITEM_RESOURCE_REL)
public interface EsServerRepository extends BaseRepository<EsServer, Long> {

    String PATH = "servers";
    String ITEM_RESOURCE_REL = "server";

    /**
     * Find {@link EsServer} by {@code name}.
     *
     * @param name
     * @return
     */
    EsServer findByName(String name);

    /**
     * Find {@link EsServer} by {@code name} and {@code serverConfig}.
     *
     * @param name
     * @param serverConfig
     * @return
     */
    EsServer findByNameAndServerConfig(String name, EsServerConfig serverConfig);

    /**
     * Find <code>EsServer</code> by {@code serverConfig}.
     *
     * @param serverConfig
     * @return
     */
    List<EsServer> findByServerConfig(EsServerConfig serverConfig);
}
