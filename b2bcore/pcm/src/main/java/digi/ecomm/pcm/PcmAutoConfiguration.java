package digi.ecomm.pcm;

import digi.ecomm.platformservice.yml.YamlPropertySourceFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:pcm.yml"}, factory = YamlPropertySourceFactory.class)
@Import({PcmConfiguration.class})
@ComponentScan(basePackageClasses = PcmAutoConfiguration.class)
public class PcmAutoConfiguration {
}
