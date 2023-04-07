package digi.ecomm.pcm.initialdata;

import digi.ecomm.entity.catalog.Catalog;
import digi.ecomm.entity.pcm.*;
import digi.ecomm.entity.pcm.CatalogVersion;
import digi.ecomm.pcm.repository.*;
import digi.ecomm.platformservice.initialdata.setup.SampleDataCreator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class ProductFeatureSampleData implements SampleDataCreator {

    private final CatalogRepository catalogRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final AttributeUnitRepository attributeUnitRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeTemplateRepository attributeTemplateRepository;
    private final ProductFeatureRepository productFeatureRepository;

    @Override
    public void createData() {
        // Attribute unit
        final AttributeUnit mAh = attributeUnitRepository.findByCode("mah").orElse(new AttributeUnit());
        mAh.setCode("mah");
        mAh.setName("mAh");
        mAh.setName(Locale.FRENCH, "mAh");

        final AttributeUnit gigabyte = attributeUnitRepository.findByCode("gigabyte").orElse(new AttributeUnit());
        gigabyte.setCode("gigabyte");
        gigabyte.setName("Gigabyte");
        gigabyte.setName(Locale.FRENCH, "Gigabyte");
        gigabyte.setSymbol("GB");
        attributeUnitRepository.saveAll(Arrays.asList(mAh, gigabyte));

        // Attribute
        final Attribute battery = attributeRepository.findByName("battery").orElse(new Attribute());
        battery.setName("battery");
        battery.setDescription("Battery capacity");
        battery.setEnabled(Boolean.TRUE);
        battery.setType(AttributeType.INTEGER);

        final Attribute ram = attributeRepository.findByName("ram").orElse(new Attribute());
        ram.setName("ram");
        ram.setDescription("RAM capacity");
        ram.setEnabled(Boolean.TRUE);
        ram.setType(AttributeType.INTEGER);
        attributeRepository.saveAll(Arrays.asList(battery, ram));

        // Attribute template
        final AttributeTemplate laptopTemplate = attributeTemplateRepository.findByName("Laptop template").orElse(new AttributeTemplate());
        laptopTemplate.setName("Laptop template");
        laptopTemplate.setEnabled(Boolean.TRUE);
        laptopTemplate.setAttributes(Stream.of(battery, ram).collect(Collectors.toSet()));
        attributeTemplateRepository.save(laptopTemplate);

        Assert.isTrue(CollectionUtils.isNotEmpty(battery.getTemplates()),
                "Fail to setup attribute template");
        Assert.isTrue(CollectionUtils.isNotEmpty(ram.getTemplates()),
                "Fail to setup attribute template");

        final Catalog productCatalog = catalogRepository.findByCodeAndVersion("productCatalog", CatalogVersion.STAGED)
                .orElseThrow(() -> new IllegalArgumentException("No productCatalog found"));

        final Category laptop = categoryRepository.findByCodeAndCatalog("laptop", productCatalog)
                .orElseThrow(() -> new IllegalArgumentException("No category laptop found"));
        laptop.setAttributeTemplates(Stream.of(laptopTemplate).collect(Collectors.toSet()));
        categoryRepository.save(laptop);
        Assert.isTrue(laptop.getAttributeTemplates().contains(laptopTemplate), "Fail to setup category template");

        // Product feature
        final Product thinkpadT490 = productRepository.findByCodeAndCatalog("thinkpad-t490", productCatalog)
                .orElseThrow(() -> new IllegalArgumentException("No product thinkpad-t49 found"));
        Optional.ofNullable(thinkpadT490.getFeatures())
                .filter(CollectionUtils::isNotEmpty)
                .ifPresent(productFeatureRepository::deleteAll);

        final ProductFeature batteryOf5000mah = new ProductFeature();
        batteryOf5000mah.setAttribute(battery);
        batteryOf5000mah.setUnit(mAh);
        batteryOf5000mah.setProduct(thinkpadT490);
        batteryOf5000mah.setRawValue("5000");

        final ProductFeature ramOf8Gb = new ProductFeature();
        ramOf8Gb.setAttribute(ram);
        ramOf8Gb.setUnit(gigabyte);
        ramOf8Gb.setProduct(thinkpadT490);
        ramOf8Gb.setRawValue("8");
        productFeatureRepository.saveAll(Arrays.asList(batteryOf5000mah, ramOf8Gb));

        Assert.isTrue(CollectionUtils.containsAll(thinkpadT490.getFeatures(), Arrays.asList(batteryOf5000mah, ramOf8Gb)),
                "Fail to setup category template");
    }
}
