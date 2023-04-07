package digi.ecomm.platformservice.c2l;

import digi.ecomm.platformservice.c2l.repository.CountryRepository;
import digi.ecomm.platformservice.c2l.repository.CurrencyRepository;
import digi.ecomm.platformservice.c2l.repository.LanguageRepository;
import digi.ecomm.platformservice.c2l.repository.RegionRepository;
import digi.ecomm.platformservice.c2l.service.CommonI18NService;
import digi.ecomm.platformservice.c2l.service.impl.CommonI18NServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class C2LConfiguration {

    private final LanguageRepository languageRepository;
    private final CurrencyRepository currencyRepository;
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;

    @Bean("commonI18NService")
    public CommonI18NService commonI18NService() {
        return new CommonI18NServiceImpl(languageRepository, currencyRepository, countryRepository);
    }

    @Bean("c2lCoreData")
    public C2LCoreData c2lCoreData() {
        return new C2LCoreData(languageRepository, countryRepository, regionRepository);
    }
}
