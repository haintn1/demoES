package digi.ecomm.platformservice;

import digi.ecomm.platformservice.util.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlatformServiceConfiguration {

    @Bean("beanUtils")
    public BeanUtils beanUtils() {
        return new BeanUtils();
    }
}
