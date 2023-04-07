package digi.ecomm.commercesearch;

import digi.ecomm.platformservice.yml.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:commercesearch.yml"}, factory = YamlPropertySourceFactory.class)
@Import({CommerceSearchConfiguration.class, IndexConfiguration.class, SearchConfiguration.class})
@EnableConfigurationProperties(SearchProperties.class)
@ComponentScan(basePackageClasses = CommerceSearchAutoConfiguration.class)
public class CommerceSearchAutoConfiguration {
}
