package digi.ecomm.commercesearch.strategy;

import digi.ecomm.commercesearch.data.IndexedPropertyGroup;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;

import java.util.List;

public interface EsIndexedPropertyGroupStrategy {

    /**
     * Group indexed properties together.
     *
     * @param indexedProperties
     * @return list of {@link IndexedPropertyGroup} or empty list
     */
    List<IndexedPropertyGroup> group(List<EsIndexedProperty> indexedProperties);
}
