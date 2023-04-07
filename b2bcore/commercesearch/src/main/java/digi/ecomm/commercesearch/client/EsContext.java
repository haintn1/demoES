package digi.ecomm.commercesearch.client;

import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import digi.ecomm.entity.commercesearch.EsIndex;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.entity.commercesearch.advance.EsAdvancedSearchConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EsContext {
    private EsFacetSearchConfig facetSearchConfig;
    private EsIndex index;
    private List<EsIndexedProperty> indexedProperties;
    private EsAdvancedSearchConfig advancedSearchConfig;
}
