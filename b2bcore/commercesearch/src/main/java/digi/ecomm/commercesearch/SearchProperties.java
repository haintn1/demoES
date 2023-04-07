package digi.ecomm.commercesearch;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "search")
@Getter
@Setter
public class SearchProperties {

    private Product product;

    @Getter
    @Setter
    public static final class Product {
        private String configName;
        private String indexEndpoint;
    }
}
