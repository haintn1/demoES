package digi.ecomm.commercesearch.provider;

import digi.ecomm.entity.commercesearch.EsIndexedProperty;

public interface FieldNameResolver {

    /**
     * Resolve field name.
     *
     * @param property
     * @return
     */
    String resolve(EsIndexedProperty property);
}
