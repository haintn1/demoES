package digi.ecomm.oms;

import digi.ecomm.platformservice.yml.YamlPropertySourceFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:oms.yml"}, factory = YamlPropertySourceFactory.class)
@Import({OmsConfiguration.class})
@ComponentScan(basePackageClasses = OmsAutoConfiguration.class)
public class OmsAutoConfiguration {
}
