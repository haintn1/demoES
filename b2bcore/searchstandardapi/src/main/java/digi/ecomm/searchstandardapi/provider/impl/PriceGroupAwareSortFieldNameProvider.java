package digi.ecomm.searchstandardapi.provider.impl;

import digi.ecomm.commercesearch.provider.impl.AbstractSortFieldNameProvider;
import digi.ecomm.entity.commercesearch.EsSortField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public class PriceGroupAwareSortFieldNameProvider extends AbstractSortFieldNameProvider {
    private static final String UNDER_SCORE = "_";

    @Override
    public String getFieldName(final EsSortField sortField) {
        final Optional<String> customerGroupIdOpt = Optional.ofNullable(RequestContextHolder.currentRequestAttributes())
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest)
                .map(request -> request.getParameter("customerGroupId"))
                .filter(StringUtils::isNotBlank);

        if (customerGroupIdOpt.isPresent()) {
            return StringUtils.join(sortField.getFieldName(), UNDER_SCORE + customerGroupIdOpt.get());
        }
        return sortField.getFieldName();
    }
}
