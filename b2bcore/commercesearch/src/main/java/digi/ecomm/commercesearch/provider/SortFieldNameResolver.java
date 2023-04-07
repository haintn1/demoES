package digi.ecomm.commercesearch.provider;

import digi.ecomm.entity.commercesearch.EsSortField;

public interface SortFieldNameResolver {

    /**
     * Resolve field name.
     *
     * @return
     */
    String resolve(EsSortField sortField);
}
