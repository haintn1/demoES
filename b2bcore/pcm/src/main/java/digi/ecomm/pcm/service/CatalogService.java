package digi.ecomm.pcm.service;

import digi.ecomm.entity.catalog.Catalog;
import digi.ecomm.entity.pcm.CatalogVersion;

public interface CatalogService {

    /**
     * Get catalog by code and version.
     *
     * @param code
     * @param version
     * @return
     */
    Catalog getByCodeAndVersion(String code, CatalogVersion version);

    /**
     * Get current catalog.
     *
     * @return
     */
    Catalog getCurrentCatalog();
}
