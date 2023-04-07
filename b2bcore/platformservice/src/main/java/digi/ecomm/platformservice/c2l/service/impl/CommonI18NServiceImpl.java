package digi.ecomm.platformservice.c2l.service.impl;

import digi.ecomm.entity.c2l.Country;
import digi.ecomm.entity.c2l.Currency;
import digi.ecomm.entity.c2l.Language;
import digi.ecomm.platformservice.c2l.repository.CountryRepository;
import digi.ecomm.platformservice.c2l.repository.CurrencyRepository;
import digi.ecomm.platformservice.c2l.repository.LanguageRepository;
import digi.ecomm.platformservice.c2l.service.CommonI18NService;
import digi.ecomm.platformservice.exception.UnknownIdentifierException;
import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
public class CommonI18NServiceImpl implements CommonI18NService {

    private final LanguageRepository languageRepository;
    private final CurrencyRepository currencyRepository;
    private final CountryRepository countryRepository;

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency getCurrencyByIsocode(final String isocode) {
        ServicesUtils.validateParameterNotNullStandardMessage("isocode", isocode); //NOSONAR
        return currencyRepository.findByIsocode(isocode).orElseThrow(
                () -> new UnknownIdentifierException(
                        String.format("No currency with isocode=%s found.", isocode)));
    }

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public Language getLanguageByIsocode(final String isocode) {
        ServicesUtils.validateParameterNotNullStandardMessage("isocode", isocode); //NOSONAR
        return languageRepository.findByIsocode(isocode).orElseThrow(
                () -> new UnknownIdentifierException(
                        String.format("No language with isocode=%s found.", isocode)));
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryByIsocode(final String isocode) {
        ServicesUtils.validateParameterNotNullStandardMessage("isocode", isocode); //NOSONAR
        return countryRepository.findByIsocode(isocode).orElseThrow(
                () -> new UnknownIdentifierException(
                        String.format("No country with isocode=%s found.", isocode)));
    }

    @Override
    public Language getCurrentLanguage() {
        final Locale currentLocale = LocaleContextHolder.getLocale();
        return getLanguageByIsocode(currentLocale.getLanguage());
    }

    @Override
    public String getCurrentLanguageCode() {
        return LocaleContextHolder.getLocale().getLanguage();
    }

    @Override
    public Locale getCurrentLocale() {
        return LocaleContextHolder.getLocale();
    }
}
