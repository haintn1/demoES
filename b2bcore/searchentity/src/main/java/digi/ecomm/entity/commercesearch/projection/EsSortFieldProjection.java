package digi.ecomm.entity.commercesearch.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.EsSort;
import digi.ecomm.entity.commercesearch.EsSortField;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsSortField.class})
public interface EsSortFieldProjection extends AbstractEntityProjection {

    String getFieldName();

    Boolean getAscending();

    EsSort getSort();
}
