package digi.ecomm.user;

import digi.ecomm.platformservice.yml.YamlPropertySourceFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:user.yml"}, factory = YamlPropertySourceFactory.class)
@Import({UserConfiguration.class})
@ComponentScan(basePackageClasses = UserAutoConfiguration.class)
public class UserAutoConfiguration {
}
