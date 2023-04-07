package digi.ecomm.pcm.initialdata;

import digi.ecomm.entity.catalog.Catalog;
import digi.ecomm.entity.pcm.CatalogVersion;
import digi.ecomm.entity.pcm.Category;
import digi.ecomm.pcm.repository.CatalogRepository;
import digi.ecomm.pcm.repository.CategoryRepository;
import digi.ecomm.platformservice.initialdata.setup.SampleDataCreator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

@RequiredArgsConstructor
public class CategorySampleData implements SampleDataCreator {

    private final CatalogRepository catalogRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void createData() {
        final Catalog productCatalog = catalogRepository.findByCodeAndVersion("productCatalog", CatalogVersion.STAGED)
                .orElseThrow(() -> new IllegalArgumentException("No productCatalog found"));
        final Category root =
                categoryRepository.findByCodeAndCatalog("root", productCatalog).orElse(new Category());
        root.setCode("root");
        root.setName("Root category");
        root.setName(Locale.FRENCH, "[FR] Root category");
        root.setCatalog(productCatalog);
        categoryRepository.save(root);

        final Category laptop =
                categoryRepository.findByCodeAndCatalog("laptop", productCatalog).orElse(new Category());
        laptop.setCode("laptop");
        laptop.setName("Laptop");
        laptop.setName(Locale.FRENCH, "[FR] Laptop");
        laptop.setCatalog(productCatalog);
        laptop.setSuperCategories(Collections.singleton(root));

        final Category mobile =
                categoryRepository.findByCodeAndCatalog("mobile", productCatalog).orElse(new Category());
        mobile.setCode("mobile");
        mobile.setName("Mobile");
        mobile.setName(Locale.FRENCH, "[FR] Mobile");
        mobile.setCatalog(productCatalog);
        mobile.setSuperCategories(Collections.singleton(root));
        categoryRepository.saveAll(Arrays.asList(laptop, mobile));

        Assert.isTrue(CollectionUtils.containsAll(root.getSubCategories(), Arrays.asList(laptop, mobile)),
                "Fail to setup super categories");
    }
}
