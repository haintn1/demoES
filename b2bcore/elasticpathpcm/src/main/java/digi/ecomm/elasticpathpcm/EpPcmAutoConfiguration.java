package digi.ecomm.elasticpathpcm;

import digi.ecomm.platformservice.yml.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:elasticpathpcm.yml"}, factory = YamlPropertySourceFactory.class)
@EnableConfigurationProperties(EpProperties.class)
@ComponentScan(basePackageClasses = EpPcmAutoConfiguration.class)
public class EpPcmAutoConfiguration {
}
