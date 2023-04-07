package digi.ecomm.platformservice.rest.springdata;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.platformservice.rest.springdata.hal.EntityModelProcessor;
import digi.ecomm.platformservice.rest.springdata.hal.HalObjectMapperBeanPostProcessor;
import digi.ecomm.platformservice.rest.springdata.hal.ResponseBodyAdviceBeanPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.support.EntityLookup;
import org.springframework.data.rest.webmvc.config.PersistentEntityResourceHandlerMethodArgumentResolver;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RootResourceInformationHandlerMethodArgumentResolver;
import org.springframework.data.rest.webmvc.mapping.Associations;
import org.springframework.data.rest.webmvc.support.BackendIdHandlerMethodArgumentResolver;
import org.springframework.data.util.Lazy;
import org.springframework.data.util.StreamUtils;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.plugin.core.PluginRegistry;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RepositoryRestSupportConfiguration implements ApplicationContextAware {

    private Lazy<RepositoryRestConfiguration> repositoryRestConfiguration;
    private Lazy<Repositories> repositories;
    private Lazy<? extends List<EntityLookup<?>>> lookups;
    private ApplicationContext context;

    @Bean("repositoryRestConfigurer")
    public RepositoryRestConfigurer repositoryRestConfigurer(final EntityManagerFactory entityManagerFactory) {
        return new CustomRepositoryRestConfigurer(entityManagerFactory);
    }

    @Bean("entityModelProcessor")
    public RepresentationModelProcessor<EntityModel<AbstractEntity>> entityModelProcessor() {
        return new EntityModelProcessor();
    }

    @Bean("responseBodyAdviceBeanPostProcessor")
    public ResponseBodyAdviceBeanPostProcessor responseBodyAdviceBeanPostProcessor() {
        return new ResponseBodyAdviceBeanPostProcessor();
    }

    @Bean("halObjectMapperBeanPostProcessor")
    public HalObjectMapperBeanPostProcessor halObjectMapperBeanPostProcessor() {
        return new HalObjectMapperBeanPostProcessor();
    }

    @Bean("persistentEntityArgumentResolverDecorator")
    public PersistentEntityResourceHandlerMethodArgumentResolver persistentEntityArgumentResolverDecorator(
            @Qualifier("defaultMessageConverters") final List<HttpMessageConverter<?>> defaultMessageConverters,
            final RootResourceInformationHandlerMethodArgumentResolver repoRequestArgumentResolver,
            final Associations associationLinks,
            final BackendIdHandlerMethodArgumentResolver backendIdHandlerMethodArgumentResolver,
            final PersistentEntities persistentEntities) {

        final PluginRegistry<EntityLookup<?>, Class<?>> entityLookups = PluginRegistry.of(getEntityLookups());

        return new PersistentEntityResourceHandlerMethodArgumentResolver(defaultMessageConverters,
                repoRequestArgumentResolver, backendIdHandlerMethodArgumentResolver,
                new EnhancedDomainObjectReader<>(persistentEntities, associationLinks), entityLookups);
    }

    @Bean("persistentEntityResourceBeanPostProcessor")
    public PersistentEntityResourceBeanPostProcessor persistentEntityResourceBeanPostProcessor() {
        return new PersistentEntityResourceBeanPostProcessor();
    }

    private List<EntityLookup<?>> getEntityLookups() {
        final List<EntityLookup<?>> allLookups = new ArrayList<>();
        allLookups.addAll(repositoryRestConfiguration.get().getEntityLookups(repositories.get()));
        allLookups.addAll(this.lookups.get());

        return allLookups;
    }

    private static <S> Lazy<List<S>> beansOfType(final ApplicationContext context, final Class<?> type) {
        return Lazy.of(() -> (List<S>) context.getBeanProvider(type)
                .orderedStream()
                .collect(StreamUtils.toUnmodifiableList()));
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        this.repositoryRestConfiguration = Lazy.of(() -> context.getBean(RepositoryRestConfiguration.class));
        this.repositories = Lazy.of(() -> context.getBean(Repositories.class));
        this.lookups = beansOfType(context, EntityLookup.class);
    }
}
