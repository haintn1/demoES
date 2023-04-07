package digi.ecomm.elasticpathsdk.context;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ApiContext {
    private final String apiUrl;
    private final String clientId;
    private final String clientSecret;
    @Setter
    private String accessToken;

    public ApiContext(final String apiUrl, final String clientId, final String clientSecret) {
        this.apiUrl = apiUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
