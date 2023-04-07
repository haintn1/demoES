package digi.ecomm.entity.commercesearch.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import digi.ecomm.entity.commercesearch.EsSynonymConfig;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsSynonymConfig.class})
public interface EsSynonymConfigProjection extends AbstractEntityProjection {

    String getSynonymFrom();

    String getSynonymTo();

    EsFacetSearchConfig getFacetSearchConfig();
}
