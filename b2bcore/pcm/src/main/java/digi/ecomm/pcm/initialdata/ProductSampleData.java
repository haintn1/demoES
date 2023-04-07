package digi.ecomm.pcm.initialdata;

import digi.ecomm.entity.catalog.Catalog;
import digi.ecomm.entity.pcm.*;
import digi.ecomm.entity.pcm.CatalogVersion;
import digi.ecomm.pcm.repository.CatalogRepository;
import digi.ecomm.pcm.repository.CategoryRepository;
import digi.ecomm.pcm.repository.ProductRepository;
import digi.ecomm.platformservice.initialdata.setup.SampleDataCreator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

@RequiredArgsConstructor
public class ProductSampleData implements SampleDataCreator {

    private final CatalogRepository catalogRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void createData() {
        final Catalog productCatalog = catalogRepository.findByCodeAndVersion("productCatalog", CatalogVersion.STAGED)
                .orElseThrow(() -> new IllegalArgumentException("No productCatalog found"));
        final Category laptop = categoryRepository.findByCodeAndCatalog("laptop", productCatalog)
                .orElseThrow(() -> new IllegalArgumentException("No category laptop found"));
        final Category mobile = categoryRepository.findByCodeAndCatalog("mobile", productCatalog)
                .orElseThrow(() -> new IllegalArgumentException("No category mobile found"));

        final Product thinkpadT490 = productRepository.findByCodeAndCatalog("thinkpad-t490", productCatalog).orElse(new Product());
        thinkpadT490.setCode("thinkpad-t490");
        thinkpadT490.setName("Lenovo Thikpad T490");
        thinkpadT490.setName(Locale.FRENCH, "[FR] Lenovo Thikpad T490");
        thinkpadT490.setCatalog(productCatalog);
        thinkpadT490.setStatus(ProductStatus.DRAFT);
        thinkpadT490.setDescription("This is a sample product");
        thinkpadT490.setDescription(Locale.FRENCH, "[FR] This is a sample product");
        thinkpadT490.setCategories(Collections.singleton(laptop));

        final Product samsungA52s = productRepository.findByCodeAndCatalog("samsung-a52s", productCatalog).orElse(new Product());
        samsungA52s.setCode("samsung-a52s");
        samsungA52s.setName("Samsung A52s");
        samsungA52s.setName(Locale.FRENCH, "[FR] Samsung A52s");
        samsungA52s.setCatalog(productCatalog);
        samsungA52s.setStatus(ProductStatus.DRAFT);
        samsungA52s.setDescription("This is a sample product");
        samsungA52s.setDescription(Locale.FRENCH, "[FR] This is a sample product");
        samsungA52s.setCategories(Collections.singleton(mobile));

        productRepository.saveAll(Arrays.asList(thinkpadT490, samsungA52s));

        Assert.isTrue(CollectionUtils.containsAny(laptop.getProducts(), thinkpadT490), "Fail to setup product categories");
        Assert.isTrue(CollectionUtils.containsAny(mobile.getProducts(), samsungA52s), "Fail to setup product categories");
    }
}
