package digi.ecomm.entity.commercesearch.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.EsServer;
import digi.ecomm.entity.commercesearch.EsServerConfig;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsServer.class})
public interface EsServerProjection extends AbstractEntityProjection {

    String getName();

    String getScheme();

    String getHostName();

    int getPort();

    EsServerConfig getServerConfig();
}
