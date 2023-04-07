package digi.ecomm.elasticpathsdk.service.auth.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.exception.ApiCommunicationException;
import digi.ecomm.elasticpathsdk.model.credential.AuthorizationToken;
import digi.ecomm.elasticpathsdk.request.auth.ClientCredentialTokenRequest;
import digi.ecomm.elasticpathsdk.service.auth.AuthorizationApi;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationApiImpl implements AuthorizationApi {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String AUTH_URL = "/oauth/access_token";

    @Override
    public AuthorizationToken getToken(final ApiContext context) {
        try {
            final ClientCredentialTokenRequest request = new ClientCredentialTokenRequest(context.getClientId(), context.getClientSecret());
            final HttpClient client = HttpClientBuilder.create().build();
            final List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("grant_type", request.getGrantType()));
            params.add(new BasicNameValuePair("client_secret", request.getClientSecret()));
            params.add(new BasicNameValuePair("client_id", request.getClientId()));

            final HttpUriRequest httpPost = RequestBuilder.post(context.getApiUrl() + AUTH_URL)
                    .setEntity(new UrlEncodedFormEntity(params)).build();
            final HttpResponse httpResponse = client.execute(httpPost);
            return OBJECT_MAPPER.readValue(EntityUtils.toString(httpResponse.getEntity()), AuthorizationToken.class);
        } catch (IOException e) {
            throw new ApiCommunicationException(e.getMessage(), e);
        }
    }
}
