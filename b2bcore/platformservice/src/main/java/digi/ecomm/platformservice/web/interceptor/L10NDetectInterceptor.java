package digi.ecomm.platformservice.web.interceptor;

import digi.ecomm.platformservice.web.RequestAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class L10NDetectInterceptor implements HandlerInterceptor {

    public static final String DEFAULT_HEADER_NAME = "L10N-Mode-Enabled";

    private final RequestAttributeService requestAttributeService;

    private String headerName = DEFAULT_HEADER_NAME;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        requestAttributeService.setAttribute(
                RequestAttributeService.L10N_MODE_ENABLED, Boolean.valueOf(request.getHeader(headerName)));
        return true;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(final String headerName) {
        this.headerName = headerName;
    }
}
