package digi.ecomm.platformservice.yml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.util.Properties;

public class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(final String name, final EncodedResource encodedResource) {
        final YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(encodedResource.getResource());
        final Properties properties = factory.getObject();
        final String fileName = encodedResource.getResource().getFilename();
        if (properties == null || fileName == null) {
            throw new IllegalArgumentException(String.format("Invalid resource %s", encodedResource));
        }
        return new PropertiesPropertySource(fileName, properties);
    }
}