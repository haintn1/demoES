package digi.ecomm.platformservice.rest.springdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.platformservice.rest.springdata.serialization.LocalizableEntityModule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;

@RequiredArgsConstructor
public class CustomRepositoryRestConfigurer implements RepositoryRestConfigurer {

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public void configureRepositoryRestConfiguration(final RepositoryRestConfiguration config, final CorsRegistry cors) {
        final Class<? extends AbstractEntity>[] managedEntityTypes = entityManagerFactory.getMetamodel()
                .getEntities().stream()
                .map(EntityType::getJavaType)
                .distinct()
                .toArray(Class[]::new);
        config.exposeIdsFor(managedEntityTypes);
        config.getExposureConfiguration().disablePutForCreation();
        config.getExposureConfiguration().disablePatchOnItemResources();
        config.getProjectionConfiguration().setParameterName("expand");
    }

    @Override
    public void configureJacksonObjectMapper(final ObjectMapper objectMapper) {
        objectMapper.registerModule(new LocalizableEntityModule());
    }
}
