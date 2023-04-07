package digi.ecomm.entity.commercesearch.advance.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.advance.EsAdvancedSearchConfig;
import digi.ecomm.entity.commercesearch.advance.EsBoostOperator;
import digi.ecomm.entity.commercesearch.advance.EsBoostRule;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsBoostRule.class})
public interface EsBoostRuleProjection extends AbstractEntityProjection {

    String getIndexProperty();

    EsBoostOperator getOperator();

    String getValue();

    Float getBoost();

    EsAdvancedSearchConfig getAdvancedSearchConfig();
}
