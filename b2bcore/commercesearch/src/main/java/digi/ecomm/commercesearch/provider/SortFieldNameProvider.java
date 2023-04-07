package digi.ecomm.commercesearch.provider;

import digi.ecomm.entity.commercesearch.EsSortField;

/**
 * Custom logic to produce field name.
 */
public interface SortFieldNameProvider {

    /**
     * Get field name.
     *
     * @return
     */
    String getFieldName(EsSortField sortField);
}
