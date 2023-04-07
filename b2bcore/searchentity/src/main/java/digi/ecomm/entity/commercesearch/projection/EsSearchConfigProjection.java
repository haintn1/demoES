package digi.ecomm.entity.commercesearch.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import digi.ecomm.entity.commercesearch.EsSearchConfig;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsSearchConfig.class})
public interface EsSearchConfigProjection extends AbstractEntityProjection {

    Integer getPageSize();

    String getDescription();

    boolean isEnableHighlighting();

    EsFacetSearchConfig getFacetSearchConfig();
}
