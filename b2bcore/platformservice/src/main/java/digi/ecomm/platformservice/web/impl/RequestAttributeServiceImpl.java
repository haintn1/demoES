package digi.ecomm.platformservice.web.impl;

import digi.ecomm.platformservice.util.ServicesUtils;
import digi.ecomm.platformservice.web.RequestAttributeService;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class RequestAttributeServiceImpl implements RequestAttributeService {

    @Override
    public <T> T getAttribute(final String name) {
        ServicesUtils.validateParameterNotNullStandardMessage("name", name);
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
        }
        return null;
    }

    @Override
    public void setAttribute(final String name, final Object value) {
        ServicesUtils.validateParameterNotNullStandardMessage("name", name);
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            requestAttributes.setAttribute(name, value, RequestAttributes.SCOPE_REQUEST);
        }
    }

    @Override
    public void removeAttribute(final String name) {
        ServicesUtils.validateParameterNotNullStandardMessage("name", name);
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            requestAttributes.removeAttribute(name, RequestAttributes.SCOPE_REQUEST);
        }
    }
}
