package digi.ecomm.searchstandardapi.provider.impl;

import digi.ecomm.commercesearch.provider.impl.AbstractFieldNameProvider;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public class PriceGroupAwareFieldNameProvider extends AbstractFieldNameProvider {
    private static final String UNDER_SCORE = "_";

    @Override
    public String getFieldName(final EsIndexedProperty property) {
        final Optional<String> customerGroupIdOpt = Optional.ofNullable(RequestContextHolder.currentRequestAttributes())
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest)
                .map(request -> request.getParameter("customerGroupId"))
                .filter(StringUtils::isNotBlank);

        if (customerGroupIdOpt.isPresent()) {
            return StringUtils.join(property.getName(), UNDER_SCORE + customerGroupIdOpt.get());
        }
        return property.getName();
    }
}
