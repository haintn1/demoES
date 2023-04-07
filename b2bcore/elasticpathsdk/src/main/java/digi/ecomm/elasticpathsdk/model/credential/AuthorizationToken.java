package digi.ecomm.elasticpathsdk.model.credential;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class AuthorizationToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("expires")
    private long expires;

    @JsonProperty("identifier")
    private String identifier;

    @JsonProperty("expires_in")
    private long expiresIn;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;
}
