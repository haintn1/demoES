package digi.ecomm.entity.commercesearch.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.entity.commercesearch.EsValueRange;
import digi.ecomm.entity.commercesearch.EsValueRangeSet;
import digi.ecomm.entity.commercesearch.EsValueRangeType;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsValueRangeSet.class})
public interface EsValueRangeSetProjection extends AbstractEntityProjection {

    String getName();

    EsValueRangeType getType();

    String getQualifier();

    List<EsValueRange> getValueRanges();

    EsIndexedProperty getIndexedProperty();
}
