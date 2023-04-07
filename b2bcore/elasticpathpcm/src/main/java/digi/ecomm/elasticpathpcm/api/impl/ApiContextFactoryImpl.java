package digi.ecomm.elasticpathpcm.api.impl;

import digi.ecomm.elasticpathpcm.EpProperties;
import digi.ecomm.elasticpathpcm.api.ApiContextFactory;
import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.credential.AuthorizationToken;
import digi.ecomm.elasticpathsdk.service.auth.AuthorizationApi;
import digi.ecomm.elasticpathsdk.service.auth.impl.AuthorizationApiImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public class ApiContextFactoryImpl implements ApiContextFactory {
    private static final long BUFFER_TIME = 3600;
    private static String accessToken;
    private static long expiresTime;

    private final EpProperties epProperties;
    private final AuthorizationApi authApi = new AuthorizationApiImpl();

    @Override
    public ApiContext get() {
        final ApiContext context = new ApiContext(epProperties.getApi().getBaseUrl(),
                epProperties.getApi().getClientId(), epProperties.getApi().getClientSecret());
        if (StringUtils.isBlank(accessToken) || expiresTime <= (System.currentTimeMillis() - BUFFER_TIME)) {
            final AuthorizationToken authorizationToken = authApi.getToken(context);
            accessToken = authorizationToken.getAccessToken(); //NOSONAR
            expiresTime = authorizationToken.getExpires(); //NOSONAR
        }
        context.setAccessToken(accessToken);
        return context;
    }
}
