package digi.ecomm.commercesearch.index.provider.impl;

import digi.ecomm.commercesearch.index.exception.FieldValueProviderException;
import digi.ecomm.commercesearch.index.provider.FieldValueProvider;
import digi.ecomm.commercesearch.index.provider.data.IndexedProduct;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import org.apache.commons.lang3.StringUtils;

public class UpperCaseProductNameValueProviderImpl extends AbstractFieldValueProvider
        implements FieldValueProvider<IndexedProduct> {

    @Override
    public Object getFieldValues(final EsIndexedProperty indexedProperty,
                                 final IndexedProduct product) throws FieldValueProviderException {

        return StringUtils.upperCase(product.getName());
    }
}
