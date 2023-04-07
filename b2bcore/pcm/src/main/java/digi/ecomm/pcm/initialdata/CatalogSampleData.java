package digi.ecomm.pcm.initialdata;

import digi.ecomm.entity.catalog.Catalog;
import digi.ecomm.entity.pcm.CatalogVersion;
import digi.ecomm.pcm.repository.CatalogRepository;
import digi.ecomm.platformservice.initialdata.setup.SampleDataCreator;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Locale;

@RequiredArgsConstructor
public class CatalogSampleData implements SampleDataCreator {

    private final CatalogRepository catalogRepository;

    @Override
    public void createData() {
        final String productCatalogCode = "productCatalog";
        final Catalog productCatalogStage =
                catalogRepository.findByCodeAndVersion(productCatalogCode, CatalogVersion.STAGED).orElse(new Catalog());
        productCatalogStage.setCode(productCatalogCode);
        productCatalogStage.setName("Product catalog staged");
        productCatalogStage.setName(Locale.FRENCH, "[FR] Product catalog staged");
        productCatalogStage.setActive(true);
        productCatalogStage.setVersion(CatalogVersion.STAGED);
        productCatalogStage.setDefaultCatalog(Boolean.TRUE);

        final Catalog productCatalogOnline =
                catalogRepository.findByCodeAndVersion(productCatalogCode, CatalogVersion.ONLINE).orElse(new Catalog());
        productCatalogOnline.setCode(productCatalogCode);
        productCatalogOnline.setName("Product catalog online");
        productCatalogOnline.setName(Locale.FRENCH, "[FR] Product catalog online");
        productCatalogOnline.setActive(true);
        productCatalogOnline.setVersion(CatalogVersion.ONLINE);
        productCatalogOnline.setDefaultCatalog(Boolean.TRUE);

        catalogRepository.saveAll(Arrays.asList(productCatalogStage, productCatalogOnline));
    }
}
