package digi.ecomm.platformservice.c2l;

import digi.ecomm.entity.c2l.Country;
import digi.ecomm.entity.c2l.Language;
import digi.ecomm.entity.c2l.Region;
import digi.ecomm.platformservice.c2l.repository.CountryRepository;
import digi.ecomm.platformservice.c2l.repository.LanguageRepository;
import digi.ecomm.platformservice.c2l.repository.RegionRepository;
import digi.ecomm.platformservice.initialdata.setup.CoreDataCreator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Locale;

@RequiredArgsConstructor
public class C2LCoreData implements CoreDataCreator {

    private final LanguageRepository languageRepository;
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;

    @Override
    public void createData() {
        crateLanguages();
        createCountriesAndRegions();
    }

    private void createCountriesAndRegions() {
        final Country canada = countryRepository.findByIsocode("CA").orElse(new Country());
        canada.setIsocode("CA");
        canada.setName(Locale.ENGLISH, "Canada");
        canada.setName(Locale.FRENCH, "[FR] Canada");
        canada.setActive(Boolean.TRUE);
        countryRepository.save(canada);

        final Region quebec = regionRepository.findByIsocode("CA-QC").orElse(new Region());
        quebec.setIsocode("CA-QC");
        quebec.setName(Locale.ENGLISH, "Quebec");
        quebec.setName(Locale.FRENCH, "Québec");
        quebec.setActive(Boolean.TRUE);
        quebec.setCountry(canada);

        final Region alberta = regionRepository.findByIsocode("CA-AB").orElse(new Region());
        alberta.setIsocode("CA-AB");
        alberta.setName(Locale.ENGLISH, "Alberta");
        alberta.setName(Locale.FRENCH, "Alberta");
        alberta.setActive(Boolean.TRUE);
        alberta.setCountry(canada);
        regionRepository.saveAll(Arrays.asList(quebec, alberta));

        Assert.isTrue(CollectionUtils.containsAll(canada.getRegions(), Arrays.asList(quebec, alberta)), "Fail to setup country");
    }

    private void crateLanguages() {
        final Language english = languageRepository.findByIsocode("en").orElse(new Language());
        english.setIsocode("en");
        english.setName("English");
        english.setActive(Boolean.TRUE);

        final Language french = languageRepository.findByIsocode("fr").orElse(new Language());
        french.setIsocode("fr");
        french.setName("French");
        french.setActive(Boolean.TRUE);
        languageRepository.saveAll(Arrays.asList(english, french));
    }
}
