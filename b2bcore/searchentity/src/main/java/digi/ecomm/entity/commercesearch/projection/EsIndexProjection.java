package digi.ecomm.entity.commercesearch.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.*;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsIndex.class})
public interface EsIndexProjection extends AbstractEntityProjection {

    String getName();

    EsIndexedEntityType getIndexedEntityType();

    List<EsSort> getSorts();

    List<EsIndexedProperty> getIndexedProperties();

    EsFacetSearchConfig getFacetSearchConfig();
}
