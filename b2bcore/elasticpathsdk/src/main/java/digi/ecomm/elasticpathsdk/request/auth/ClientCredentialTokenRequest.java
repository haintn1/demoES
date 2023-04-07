package digi.ecomm.elasticpathsdk.request.auth;

public class ClientCredentialTokenRequest {
    private String grantType = "client_credentials";
    private String clientSecret;
    private String clientId;

    public ClientCredentialTokenRequest(final String clientId, final String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getClientId() {
        return clientId;
    }
}
