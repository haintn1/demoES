package digi.ecomm.platformservice.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    /**
     * Get bean of type {@code beanClazz}.
     *
     * @param beanClazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(final Class<T> beanClazz) {
        return context.getBean(beanClazz);
    }

    /**
     * Get bean with name {@code name} of type {@code beanClazz}.
     *
     * @param name
     * @param beanClazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(final String name, final Class<T> beanClazz) {
        return context.getBean(name, beanClazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        context = applicationContext; //NOSONAR
    }
}
