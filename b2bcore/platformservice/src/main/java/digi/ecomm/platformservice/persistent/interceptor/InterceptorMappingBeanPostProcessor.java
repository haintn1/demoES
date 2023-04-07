package digi.ecomm.platformservice.persistent.interceptor;

import digi.ecomm.entity.AbstractEntity;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InterceptorMappingBeanPostProcessor implements BeanPostProcessor, BeanFactoryAware {

    private static final String BEAN = "interceptorMapping";

    private BeanFactory beanFactory;

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        if (bean instanceof Interceptor) {
            final Map<Class<? extends AbstractEntity>, List<Interceptor<? extends AbstractEntity>>> interceptorMap =
                    beanFactory.getBean(BEAN, Map.class);
            final Class<? extends AbstractEntity> targetType = ((Interceptor<? extends AbstractEntity>) bean).targetType();
            populate(bean, targetType, interceptorMap);
        }
        return bean;
    }

    private void populate(final Object bean, final Class<? extends AbstractEntity> targetType,
                          final Map<Class<? extends AbstractEntity>, List<Interceptor<? extends AbstractEntity>>> interceptorMap) {
        List<Interceptor<? extends AbstractEntity>> interceptors = interceptorMap.get(targetType);
        if (interceptors == null) {
            interceptors = new ArrayList<>();
        }
        interceptors.add((Interceptor) bean);
        interceptorMap.put(targetType, interceptors);
    }

    @Override
    public void setBeanFactory(final BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
