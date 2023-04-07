package digi.ecomm.entity.commercesearch.advance.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import digi.ecomm.entity.commercesearch.advance.EsAdvancedSearchConfig;
import digi.ecomm.entity.commercesearch.advance.EsBoostRule;
import digi.ecomm.entity.commercesearch.advance.EsBoostRulesMergeMode;
import digi.ecomm.entity.commercesearch.advance.EsPromotedItem;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsAdvancedSearchConfig.class})
public interface EsAdvancedSearchConfigProjection extends AbstractEntityProjection {

    String getDescription();

    EsBoostRulesMergeMode getBoostRulesMergeMode();

    List<EsBoostRule> getBoostRules();

    List<EsPromotedItem> getPromotedItems();

    EsFacetSearchConfig getFacetSearchConfig();
}
