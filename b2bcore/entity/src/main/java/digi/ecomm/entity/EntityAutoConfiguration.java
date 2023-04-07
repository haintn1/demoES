package digi.ecomm.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "digi.ecomm")
@ComponentScan(basePackageClasses = EntityAutoConfiguration.class)
public class EntityAutoConfiguration {
}
