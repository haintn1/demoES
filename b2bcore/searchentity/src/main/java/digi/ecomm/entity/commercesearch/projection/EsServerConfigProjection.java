package digi.ecomm.entity.commercesearch.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import digi.ecomm.entity.commercesearch.EsServer;
import digi.ecomm.entity.commercesearch.EsServerConfig;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsServerConfig.class})
public interface EsServerConfigProjection extends AbstractEntityProjection {

    String getName();

    Integer getConnectionTimeout();

    Integer getSocketTimeout();

    String getUsername();

    String getPassword();

    Integer getNumShards();

    Integer getReplicationFactor();

    List<EsServer> getServers();

    EsFacetSearchConfig getFacetSearchConfig();
}
