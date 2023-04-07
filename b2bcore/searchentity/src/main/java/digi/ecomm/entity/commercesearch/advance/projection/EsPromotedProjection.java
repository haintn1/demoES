package digi.ecomm.entity.commercesearch.advance.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.advance.EsAdvancedSearchConfig;
import digi.ecomm.entity.commercesearch.advance.EsPromotedItem;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsPromotedItem.class})
public interface EsPromotedProjection extends AbstractEntityProjection {

    Long getItemId();

    EsAdvancedSearchConfig getAdvancedSearchConfig();
}
