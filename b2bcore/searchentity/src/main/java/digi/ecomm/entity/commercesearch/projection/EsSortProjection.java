package digi.ecomm.entity.commercesearch.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.EsIndex;
import digi.ecomm.entity.commercesearch.EsSort;
import digi.ecomm.entity.commercesearch.EsSortField;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsSort.class})
public interface EsSortProjection extends AbstractEntityProjection {

    String getCode();

    String getName();

    Boolean getUseBoost();

    List<EsSortField> getSortFields();

    EsIndex getIndex();
}
