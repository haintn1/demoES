package digi.ecomm.platformservice.rest.springdata.hal;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Collections;

public class ResponseBodyAdviceBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        if (bean instanceof RequestMappingHandlerAdapter) {
            final RequestMappingHandlerAdapter adapter = (RequestMappingHandlerAdapter) bean;
            adapter.setResponseBodyAdvice(Collections.singletonList(new RepresentationModelResponseBodyAdvice<>()));
        }
        return bean;
    }
}
