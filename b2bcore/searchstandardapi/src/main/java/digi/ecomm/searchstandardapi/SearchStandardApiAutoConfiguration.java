package digi.ecomm.searchstandardapi;

import digi.ecomm.platformservice.yml.YamlPropertySourceFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = {SearchStandardApiAutoConfiguration.class})
@Import({SearchStandardApiConfiguration.class, WebSecurityConfiguration.class})
@PropertySource(value = {"classpath:searchstandardapi.yml"}, factory = YamlPropertySourceFactory.class)
public class SearchStandardApiAutoConfiguration {
}
