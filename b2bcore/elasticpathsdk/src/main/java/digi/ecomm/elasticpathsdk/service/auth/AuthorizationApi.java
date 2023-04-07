package digi.ecomm.elasticpathsdk.service.auth;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.credential.AuthorizationToken;

public interface AuthorizationApi {
    /**
     * Get token.
     *
     * @param context
     * @return
     */
    AuthorizationToken getToken(ApiContext context);
}
