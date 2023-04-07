package digi.ecomm.pcm.service.impl;

import digi.ecomm.entity.catalog.Catalog;
import digi.ecomm.entity.pcm.CatalogVersion;
import digi.ecomm.pcm.repository.CatalogRepository;
import digi.ecomm.pcm.service.CatalogService;
import digi.ecomm.platformservice.exception.UnknownIdentifierException;
import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepository catalogRepository;

    @Override
    public Catalog getByCodeAndVersion(final String code, final CatalogVersion version) {
        ServicesUtils.validateParameterNotNullStandardMessage("code", code);
        ServicesUtils.validateParameterNotNullStandardMessage("version", version);

        return catalogRepository.findByCodeAndVersion(code, version)
                .orElseThrow(() -> new UnknownIdentifierException(
                        String.format("No catalog found with [code=%s] and [version=%s]", code, version)));
    }

    @Override
    public Catalog getCurrentCatalog() {
        return getByCodeAndVersion("productCatalog", CatalogVersion.STAGED);
    }
}
