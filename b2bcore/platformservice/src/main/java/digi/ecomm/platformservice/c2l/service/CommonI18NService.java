package digi.ecomm.platformservice.c2l.service;

import digi.ecomm.entity.c2l.Country;
import digi.ecomm.entity.c2l.Currency;
import digi.ecomm.entity.c2l.Language;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Locale;

public interface CommonI18NService {

    /**
     * Get all currencies.
     *
     * @return List of {@link digi.ecomm.entity.c2l.Currency}s or empty list
     */
    List<Currency> getAllCurrencies();

    /**
     * Get all currency by isocode.
     *
     * @return {@link digi.ecomm.entity.c2l.Currency}
     * @throws {@link digi.ecomm.platformservice.exception.UnknownIdentifierException} if no currency found
     */
    Currency getCurrencyByIsocode(String isocode);

    /**
     * Get all languages.
     *
     * @return List of {@link Language}s or empty list
     */
    List<Language> getAllLanguages();

    /**
     * Get language by isocode.
     *
     * @param isocode
     * @return {@link Language}
     * @throws {@link digi.ecomm.platformservice.exception.UnknownIdentifierException} if no language found
     */
    Language getLanguageByIsocode(String isocode);

    /**
     * Get all countries.
     *
     * @return List of {@link digi.ecomm.entity.c2l.Country}s or empty list
     */
    List<Country> getAllCountries();

    /**
     * Get country by isocode.
     *
     * @param isocode
     * @return {@link digi.ecomm.entity.c2l.Country}
     * @throws {@link digi.ecomm.platformservice.exception.UnknownIdentifierException} if no country found
     */
    Country getCountryByIsocode(String isocode);

    /**
     * Get the current language.
     *
     * @return {@link Language}
     * @throws {@link digi.ecomm.platformservice.exception.UnknownIdentifierException} if no language found
     */
    Language getCurrentLanguage();

    /**
     * Get the current language isocode.
     *
     * @return language ioscode
     */
    @NonNull
    String getCurrentLanguageCode();

    /**
     * Get the current locale.
     *
     * @return
     */
    @NonNull
    Locale getCurrentLocale();
}
