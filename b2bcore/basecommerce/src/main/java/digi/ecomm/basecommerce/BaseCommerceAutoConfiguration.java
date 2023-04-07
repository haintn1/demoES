package digi.ecomm.basecommerce;

import digi.ecomm.platformservice.yml.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:basecommerce.yml"}, factory = YamlPropertySourceFactory.class)
@EnableConfigurationProperties(BaseCommerceProperties.class)
@ComponentScan(basePackageClasses = BaseCommerceAutoConfiguration.class)
public class BaseCommerceAutoConfiguration {
}
