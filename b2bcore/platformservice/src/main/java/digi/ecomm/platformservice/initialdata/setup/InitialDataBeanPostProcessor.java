package digi.ecomm.platformservice.initialdata.setup;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;

public class InitialDataBeanPostProcessor implements BeanPostProcessor, BeanFactoryAware {

    private static final String CORE_DATA_CREATORS_BEAN = "coreDataCreators";
    private static final String SAMPLE_DATA_CREATORS_BEAN = "sampleDataCreators";

    private BeanFactory beanFactory;

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        if (bean instanceof CoreDataCreator) {
            final List<CoreDataCreator> coreDataCreators = beanFactory.getBean(CORE_DATA_CREATORS_BEAN, List.class);
            coreDataCreators.add((CoreDataCreator) bean);
        } else if (bean instanceof SampleDataCreator) {
            final List<SampleDataCreator> sampleDataCreators = beanFactory.getBean(SAMPLE_DATA_CREATORS_BEAN, List.class);
            sampleDataCreators.add((SampleDataCreator) bean);
        }
        return bean;
    }

    @Override
    public void setBeanFactory(final BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
