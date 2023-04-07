package digi.ecomm.entity.commercesearch.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.EsValueRange;
import digi.ecomm.entity.commercesearch.EsValueRangeSet;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsValueRange.class})
public interface EsValueRangeProjection extends AbstractEntityProjection {

    String getName();

    String getValueFrom();

    String getValueTo();

    EsValueRangeSet getValueRangeSet();
}
