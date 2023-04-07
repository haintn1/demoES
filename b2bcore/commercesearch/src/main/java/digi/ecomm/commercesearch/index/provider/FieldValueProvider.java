package digi.ecomm.commercesearch.index.provider;

import digi.ecomm.commercesearch.index.exception.FieldValueProviderException;
import digi.ecomm.datatransferobject.search.AbstractIndexedSourceItem;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;

/**
 * Custom logic to produce field value.
 */
public interface FieldValueProvider<T extends AbstractIndexedSourceItem> {

    /**
     * Get field values.
     *
     * @param indexedProperty
     * @param sourceObj
     * @return
     * @throws FieldValueProviderException
     */
    Object getFieldValues(EsIndexedProperty indexedProperty, T sourceObj) throws FieldValueProviderException;
}
