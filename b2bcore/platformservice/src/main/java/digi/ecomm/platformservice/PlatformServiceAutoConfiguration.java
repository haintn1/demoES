package digi.ecomm.platformservice;

import digi.ecomm.platformservice.yml.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:platformservice.yml"}, factory = YamlPropertySourceFactory.class)
@EnableConfigurationProperties(PlatformProperties.class)
@ComponentScan(basePackageClasses = PlatformServiceAutoConfiguration.class)
public class PlatformServiceAutoConfiguration {
}
