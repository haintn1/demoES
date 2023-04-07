package digi.ecomm.elasticpathsdk.service.pcm.catalog;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.catalog.CatalogLatestReleaseModel;
import digi.ecomm.elasticpathsdk.model.catalog.CatalogModel;
import digi.ecomm.elasticpathsdk.model.catalog.CatalogsModel;

public interface CatalogApi {
    /**
     * Get all catalogs.
     *
     * @param context
     * @return
     */
    CatalogsModel getAllCatalogs(ApiContext context);

    /**
     * Get catalog by id.
     *
     * @param catalogId
     * @param context
     * @return
     */
    CatalogModel getCatalog(String catalogId, ApiContext context);

    /**
     * Get catalog latest release.
     *
     * @param catalogId
     * @param context
     * @return
     */
    CatalogLatestReleaseModel getCatalogLatestRelease(String catalogId, ApiContext context);
}
