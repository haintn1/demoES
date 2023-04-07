package digi.ecomm.corestandardapi;

import digi.ecomm.corestandardapi.web.WebSecurityConfiguration;
import digi.ecomm.platformservice.yml.YamlPropertySourceFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:corestandardapi.yml"}, factory = YamlPropertySourceFactory.class)
@Import({CoreStandardApiConfiguration.class, WebSecurityConfiguration.class})
@ComponentScan(basePackageClasses = CoreStandardApiAutoConfiguration.class)
public class CoreStandardApiAutoConfiguration {
}
