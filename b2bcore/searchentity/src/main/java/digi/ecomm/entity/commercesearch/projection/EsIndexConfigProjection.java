package digi.ecomm.entity.commercesearch.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import digi.ecomm.entity.commercesearch.EsIndexConfig;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsIndexConfig.class})
public interface EsIndexConfigProjection extends AbstractEntityProjection {

    String getName();

    int getBatchSize();

    int getBatchBytes();

    int getNumConcurrentRequests();

    int getFlushIntervalSeconds();

    int getMaxRetries();

    int getRetryDelaySeconds();

    boolean isDynamicMappingAllowed();

    EsFacetSearchConfig getFacetSearchConfig();
}
