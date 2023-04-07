package digi.ecomm.platformservice.rest.springdata;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class PersistentEntityResourceBeanPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {
        final DefaultListableBeanFactory factory = (DefaultListableBeanFactory) beanFactory;
        factory.removeBeanDefinition("persistentEntityArgumentResolver");
    }
}
