package digi.ecomm.platformservice.rest.springdata.hal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.TypeConstrainedMappingJackson2HttpMessageConverter;

public class HalObjectMapperBeanPostProcessor implements BeanPostProcessor {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        if (bean instanceof TypeConstrainedMappingJackson2HttpMessageConverter
                && "halJacksonHttpMessageConverter".equals(beanName)) {
            final TypeConstrainedMappingJackson2HttpMessageConverter converter = (TypeConstrainedMappingJackson2HttpMessageConverter) bean;
            final ObjectMapper objectMapper = converter.getObjectMapper();
            objectMapper.addMixIn(CollectionModel.class, SimplePagedModelMixin.class);
        }
        return bean;
    }
}
