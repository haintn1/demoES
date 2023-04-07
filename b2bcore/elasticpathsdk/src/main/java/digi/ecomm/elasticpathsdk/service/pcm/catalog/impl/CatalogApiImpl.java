package digi.ecomm.elasticpathsdk.service.pcm.catalog.impl;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.catalog.CatalogLatestReleaseModel;
import digi.ecomm.elasticpathsdk.model.catalog.CatalogModel;
import digi.ecomm.elasticpathsdk.model.catalog.CatalogsModel;
import digi.ecomm.elasticpathsdk.service.AbstractService;
import digi.ecomm.elasticpathsdk.service.pcm.catalog.CatalogApi;
import org.apache.http.client.config.RequestConfig;


public class CatalogApiImpl extends AbstractService implements CatalogApi {
    private static final String CATALOG_ID = ":catalogId";
    private static final String CATALOGS_URL = "/pcm/catalogs";
    private static final String CATALOG_URL = "/pcm/catalogs/:catalogId";
    private static final String CATALOG_LATEST_RELEASE_URL = "/pcm/catalogs/:catalogId/releases/latest";

    public CatalogApiImpl() {
    }

    public CatalogApiImpl(final int connectTimeout, final int socketTimeout) {
        super(connectTimeout, socketTimeout);
    }

    public CatalogApiImpl(final RequestConfig requestConfig) {
        super(requestConfig);
    }

    @Override
    public CatalogsModel getAllCatalogs(final ApiContext context) {
        return executeGetRequest(CATALOGS_URL, CatalogsModel.class, context);
    }

    @Override
    public CatalogModel getCatalog(final String catalogId, final ApiContext context) {
        return executeGetRequest(CATALOG_URL.replace(CATALOG_ID, catalogId), CatalogModel.class, context);
    }

    @Override
    public CatalogLatestReleaseModel getCatalogLatestRelease(final String catalogId, final ApiContext context) {
        return executeGetRequest(CATALOG_LATEST_RELEASE_URL.replace(CATALOG_ID, catalogId),
                CatalogLatestReleaseModel.class, context);
    }
}
