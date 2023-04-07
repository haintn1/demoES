package digi.ecomm.commercesearch.provider.impl;

import digi.ecomm.commercesearch.provider.FieldNameProvider;
import digi.ecomm.commercesearch.provider.FieldNameResolver;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.platformservice.util.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public class FieldNameResolverImpl implements FieldNameResolver {

    @Override
    public String resolve(final EsIndexedProperty property) {
        final String fieldName;
        if (StringUtils.isNotBlank(property.getFieldNameProvider())) {
            fieldName = BeanUtils.getBean(property.getFieldNameProvider(), FieldNameProvider.class)
                    .getFieldName(property);
        } else {
            fieldName = property.getName();
        }

        return fieldName;
    }
}
