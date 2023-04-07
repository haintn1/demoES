package digi.ecomm.commercesearch.provider.impl;

import digi.ecomm.commercesearch.provider.SortFieldNameProvider;
import digi.ecomm.commercesearch.provider.SortFieldNameResolver;
import digi.ecomm.entity.commercesearch.EsSortField;
import digi.ecomm.platformservice.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;

public class SortFieldNameResolverImpl implements SortFieldNameResolver {
    @Override
    public String resolve(final EsSortField sortField) {
        final String fieldName;
        if (StringUtils.isNotBlank(sortField.getFieldNameProvider())) {
            fieldName = BeanUtils.getBean(sortField.getFieldNameProvider(), SortFieldNameProvider.class)
                    .getFieldName(sortField);
        } else {
            fieldName = sortField.getFieldName();
        }

        return fieldName;
    }
}
