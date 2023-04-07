package digi.ecomm.commercesearch.data;

import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class IndexedPropertyGroup {
    private EsIndexedProperty property;
    private List<EsIndexedProperty> nestedProperties;
}
