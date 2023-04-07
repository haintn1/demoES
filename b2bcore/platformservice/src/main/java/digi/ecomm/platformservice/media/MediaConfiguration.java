package digi.ecomm.platformservice.media;

import digi.ecomm.platformservice.media.repository.MediaRepository;
import digi.ecomm.platformservice.media.service.MediaService;
import digi.ecomm.platformservice.media.service.impl.MediaServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = MediaConfiguration.class)
public class MediaConfiguration {

    @Bean("mediaService")
    public MediaService mediaService(final MediaRepository mediaRepository) {
        return new MediaServiceImpl(mediaRepository);
    }
}
