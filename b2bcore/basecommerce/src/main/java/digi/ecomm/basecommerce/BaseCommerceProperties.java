package digi.ecomm.basecommerce;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "basecommerce")
@Getter
@Setter
public class BaseCommerceProperties {
}
