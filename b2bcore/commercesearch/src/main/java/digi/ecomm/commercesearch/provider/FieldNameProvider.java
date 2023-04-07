package digi.ecomm.commercesearch.provider;

import digi.ecomm.entity.commercesearch.EsIndexedProperty;

/**
 * Custom logic to produce field name.
 */
public interface FieldNameProvider {

    /**
     * Get field name.
     *
     * @return
     */
    String getFieldName(EsIndexedProperty property);
}
