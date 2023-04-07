package digi.ecomm.elasticpathsdk.service.pcm.hierarchy.impl;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.hierarchy.HierarchiesModel;
import digi.ecomm.elasticpathsdk.model.hierarchy.HierarchyModel;
import digi.ecomm.elasticpathsdk.service.AbstractService;
import digi.ecomm.elasticpathsdk.service.pcm.hierarchy.HierarchyApi;
import org.apache.http.client.config.RequestConfig;


public class HierarchyApiImpl extends AbstractService implements HierarchyApi {
    private static final String CATALOG_ID = ":catalogId";
    private static final String HIERARCHY_ID = ":hierarchyId";
    private static final String HIERARCHIES_URL = "/pcm/catalogs/:catalogId/releases/latest/hierarchies";
    private static final String ALL_HIERARCHIES_URL = "/pcm/hierarchies";
    private static final String A_HIERARCHY_URL = "/pcm/hierarchies/:hierarchyId";
    private static final String CREATE_HIERARCHIES_URL = "/pcm/hierarchies/";
    private static final String UPDATE_HIERARCHIES_URL = "/pcm/hierarchies/:hierarchyId";
    private static final String DELETE_HIERARCHIES_URL = "/pcm/hierarchies/:hierarchyId";

    public HierarchyApiImpl() {
    }

    public HierarchyApiImpl(final int connectTimeout, final int socketTimeout) {
        super(connectTimeout, socketTimeout);
    }

    public HierarchyApiImpl(final RequestConfig requestConfig) {
        super(requestConfig);
    }

    @Override
    public HierarchiesModel getAllHierarchiesInTheLatestRelease(final String catalogId, final ApiContext context) {
        return executeGetRequest(HIERARCHIES_URL.replace(CATALOG_ID, catalogId), HierarchiesModel.class, context);
    }

    @Override
    public HierarchiesModel getAllHierarchies(final ApiContext context) {
        return executeGetRequest(ALL_HIERARCHIES_URL, HierarchiesModel.class, context);
    }

    @Override
    public HierarchyModel getHierarchy(final String hierarchyId, final ApiContext context) {
        return executeGetRequest(A_HIERARCHY_URL.replace(HIERARCHY_ID, hierarchyId), HierarchyModel.class, context);
    }

    @Override
    public HierarchyModel createHierarchy(final HierarchyModel hierarchyModel, final ApiContext context) {
        return executePostRequest(CREATE_HIERARCHIES_URL, hierarchyModel, HierarchyModel.class, context);
    }

    @Override
    public HierarchyModel updateHierarchy(final HierarchyModel hierarchyModel, final String hierarchyId, final ApiContext context) {
        return executePutRequest(UPDATE_HIERARCHIES_URL.replace(HIERARCHY_ID, hierarchyId), hierarchyModel,
                HierarchyModel.class, context);
    }

    @Override
    public void deleteHierarchy(final String hierarchyId, final ApiContext context) {
        executeDeleteRequest(DELETE_HIERARCHIES_URL.replace(HIERARCHY_ID, hierarchyId), context);
    }
}
