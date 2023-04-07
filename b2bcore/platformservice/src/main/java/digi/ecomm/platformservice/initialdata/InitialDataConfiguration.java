package digi.ecomm.platformservice.initialdata;

import digi.ecomm.platformservice.PlatformProperties;
import digi.ecomm.platformservice.initialdata.setup.CoreDataCreator;
import digi.ecomm.platformservice.initialdata.setup.InitialDataBeanPostProcessor;
import digi.ecomm.platformservice.initialdata.setup.InitialDataSetup;
import digi.ecomm.platformservice.initialdata.setup.SampleDataCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
@ComponentScan(basePackageClasses = InitialDataConfiguration.class)
public class InitialDataConfiguration {

    private final PlatformProperties platformProperties;

    @Bean("initialDataSetup")
    public InitialDataSetup initialDataSetup(@Qualifier("coreDataCreators") final List<CoreDataCreator> coreDataCreators,
                                             @Qualifier("sampleDataCreators") final List<SampleDataCreator> sampleDataCreators) {
        return new InitialDataSetup(coreDataCreators, sampleDataCreators, platformProperties);
    }

    @Bean("coreDataCreators")
    public List<CoreDataCreator> coreDataCreators() {
        return new ArrayList<>();
    }

    @Bean("sampleDataCreators")
    public List<SampleDataCreator> sampleDataCreators() {
        return new ArrayList<>();
    }

    @Bean
    public InitialDataBeanPostProcessor initialDataBeanPostProcessor() {
        return new InitialDataBeanPostProcessor();
    }
}
