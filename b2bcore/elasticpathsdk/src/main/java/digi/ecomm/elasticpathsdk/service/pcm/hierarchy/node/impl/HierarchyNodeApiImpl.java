package digi.ecomm.elasticpathsdk.service.pcm.hierarchy.node.impl;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.hierarchy.node.HierarchyNodeModel;
import digi.ecomm.elasticpathsdk.service.AbstractService;
import digi.ecomm.elasticpathsdk.service.pcm.hierarchy.node.HierarchyNodeApi;
import org.apache.http.client.config.RequestConfig;


public class HierarchyNodeApiImpl extends AbstractService implements HierarchyNodeApi {
    private static final String HIERARCHY_ID = ":hierarchyId";
    private static final String NODE_ID = ":nodeId";
    private static final String A_HIERARCHY_NODE_URL = "/pcm/hierarchies/:hierarchyId/nodes/:nodeId";
    private static final String CREATE_HIERARCHY_NODE_URL = "/pcm/hierarchies/:hierarchyId/nodes";
    private static final String UPDATE_HIERARCHY_NODE_URL = "/pcm/hierarchies/:hierarchyId/nodes/:nodeId";
    private static final String DELETE_HIERARCHY_NODE_URL = "/pcm/hierarchies/:hierarchyId/nodes/:nodeId";

    public HierarchyNodeApiImpl() {
    }

    public HierarchyNodeApiImpl(final int connectTimeout, final int socketTimeout) {
        super(connectTimeout, socketTimeout);
    }

    public HierarchyNodeApiImpl(final RequestConfig requestConfig) {
        super(requestConfig);
    }

    @Override
    public HierarchyNodeModel getHierarchyNode(final String hierarchyId, final String nodeId, final ApiContext context) {
        return executeGetRequest(
                A_HIERARCHY_NODE_URL.replace(HIERARCHY_ID, hierarchyId).replace(NODE_ID, nodeId),
                HierarchyNodeModel.class, context);
    }

    @Override
    public HierarchyNodeModel createHierarchyNode(final HierarchyNodeModel hierarchyNodeModel,
                                                  final String hierarchyId, final ApiContext context) {
        return executePostRequest(CREATE_HIERARCHY_NODE_URL.replace(HIERARCHY_ID, hierarchyId),
                hierarchyNodeModel, HierarchyNodeModel.class, context);
    }

    @Override
    public HierarchyNodeModel updateHierarchyNode(final HierarchyNodeModel hierarchyNodeModel,
                                                  final String hierarchyId, final String nodeId, final ApiContext context) {
        return executePutRequest(
                UPDATE_HIERARCHY_NODE_URL.replace(HIERARCHY_ID, hierarchyId).replace(NODE_ID, nodeId),
                hierarchyNodeModel, HierarchyNodeModel.class, context);
    }

    @Override
    public void deleteHierarchyNode(final String hierarchyId, final String nodeId, final ApiContext context) {
        executeDeleteRequest(DELETE_HIERARCHY_NODE_URL.replace(HIERARCHY_ID, hierarchyId)
                .replace(NODE_ID, nodeId), context);
    }
}
