package digi.ecomm.elasticpathpcm;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ep")
@Getter
@Setter
public class EpProperties {

    private Api api;

    @Getter
    @Setter
    public static final class Api {
        private String baseUrl;
        private String clientId;
        private String clientSecret;
        private int connectTimeout = -1;
        private int socketTimeout = -1;
    }
}
