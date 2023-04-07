package digi.ecomm.platformservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "platform")
@Getter
@Setter
public class PlatformProperties {

    private InitialData initialData;
    private Media media;

    @Getter
    @Setter
    public static final class Media {
        private String rootDir;
    }

    @Getter
    @Setter
    public static final class InitialData {
        private Core core;
        private Sample sample;
    }

    @Getter
    @Setter
    public static final class Core {
        private boolean autoImport;
    }

    @Getter
    @Setter
    public static final class Sample {
        private boolean autoImport;
    }
}
