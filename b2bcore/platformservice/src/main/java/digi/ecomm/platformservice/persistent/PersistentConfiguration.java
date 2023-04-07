package digi.ecomm.platformservice.persistent;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.platformservice.persistent.hibernate.HibernatePropertiesCustomization;
import digi.ecomm.platformservice.persistent.interceptor.GenericEntityInterceptor;
import digi.ecomm.platformservice.persistent.interceptor.Interceptor;
import digi.ecomm.platformservice.persistent.interceptor.InterceptorMappingBeanPostProcessor;
import digi.ecomm.platformservice.persistent.repository.impl.BaseRepositoryImpl;
import digi.ecomm.platformservice.persistent.service.EntityService;
import digi.ecomm.platformservice.persistent.service.impl.EntityServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "digi.ecomm", repositoryBaseClass = BaseRepositoryImpl.class)
@EnableTransactionManagement
@ComponentScan(basePackageClasses = PersistentConfiguration.class)
@EnableSpringConfigured
public class PersistentConfiguration {

    @Bean("hibernatePropertiesCustomizer")
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(
            @Qualifier("genericEntityInterceptor") final GenericEntityInterceptor interceptor) {
        return new HibernatePropertiesCustomization(interceptor);
    }

    @Bean("entityService")
    public EntityService entityService(final EntityManager entityManager, final PlatformTransactionManager txManager) {
        return new EntityServiceImpl(entityManager, txManager);
    }

    @Bean("genericEntityInterceptor")
    public GenericEntityInterceptor genericEntityInterceptor() {
        return new GenericEntityInterceptor();
    }

    @Bean("interceptorMapping")
    public <T extends AbstractEntity> Map<Class<T>, List<Interceptor<T>>> interceptorMapping() {
        return new HashMap<>();
    }

    @Bean("interceptorMappingBeanPostProcessor")
    public BeanPostProcessor interceptorMappingBeanPostProcessor() {
        return new InterceptorMappingBeanPostProcessor();
    }
}
