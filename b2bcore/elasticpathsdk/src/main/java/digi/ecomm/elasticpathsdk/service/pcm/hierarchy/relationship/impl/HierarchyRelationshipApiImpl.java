package digi.ecomm.elasticpathsdk.service.pcm.hierarchy.relationship.impl;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.hierarchy.node.HierarchyNodeModel;
import digi.ecomm.elasticpathsdk.model.hierarchy.relationship.HierarchyNodeChildrenModel;
import digi.ecomm.elasticpathsdk.model.hierarchy.relationship.HierarchyNodeProductModel;
import digi.ecomm.elasticpathsdk.model.hierarchy.relationship.HierarchyNodeRelationshipChildrenModel;
import digi.ecomm.elasticpathsdk.service.AbstractService;
import digi.ecomm.elasticpathsdk.service.pcm.hierarchy.relationship.HierarchyRelationshipApi;
import org.apache.http.client.config.RequestConfig;


public class HierarchyRelationshipApiImpl extends AbstractService implements HierarchyRelationshipApi {
    private static final String HIERARCHY_ID = ":hierarchyId";
    private static final String NODE_ID = ":nodeId";
    private static final String GET_A_NODE_CHILDREN = "/pcm/hierarchies/:hierarchyId/nodes/:nodeId/children";
    private static final String GET_A_NODE_PRODUCT = "/pcm/hierarchies/:hierarchyId/nodes/:nodeId/products";
    private static final String CREATE_RELATIONSHIP_NODE_AND_CHILD =
            "/pcm/hierarchies/:hierarchyID/nodes/:parentNodeId/relationships/children";
    private static final String CREATE_RELATIONSHIP_NODE_AND_PRODUCT =
            "/pcm/hierarchies/:hierarchyId/nodes/:nodeId/relationships/products";
    private static final String DELETE_NODE_PARENT_RELATIONSHIP =
            "/pcm/hierarchies/:hierarchyId/nodes/:nodeId/relationships/parent";
    private static final String DELETE_RELATIONSHIP_NODE_AND_PRODUCT =
            "/pcm/hierarchies/:hierarchyId/nodes/:nodeId/relationships/products";
    private static final String UPDATE_NODE_PARENT_RELATIONSHIP =
            "/pcm/hierarchies/:hierarchyId/nodes/:nodeId/relationships/parent";

    public HierarchyRelationshipApiImpl() {
    }

    public HierarchyRelationshipApiImpl(final int connectTimeout, final int socketTimeout) {
        super(connectTimeout, socketTimeout);
    }

    public HierarchyRelationshipApiImpl(final RequestConfig requestConfig) {
        super(requestConfig);
    }

    @Override
    public HierarchyNodeChildrenModel getNodeChildren(final String hierarchyId, final String nodeId,
                                                      final ApiContext context) {
        return executeGetRequest(GET_A_NODE_CHILDREN.replace(HIERARCHY_ID, hierarchyId).replace(NODE_ID, nodeId),
                HierarchyNodeChildrenModel.class, context);
    }

    @Override
    public HierarchyNodeProductModel getNodeProducts(final String hierarchyId, final String nodeId, final ApiContext context) {
        return executeGetRequest(GET_A_NODE_PRODUCT.replace(HIERARCHY_ID, hierarchyId).replace(NODE_ID, nodeId),
                HierarchyNodeProductModel.class, context);
    }

    @Override
    public HierarchyNodeRelationshipChildrenModel createRelationshipNodeAndChild(
            final HierarchyNodeChildrenModel hierarchyNodeChildrenModel, final String hierarchyId, final String nodeId,
            final ApiContext context) {
        return executePostRequest(CREATE_RELATIONSHIP_NODE_AND_CHILD.replace(HIERARCHY_ID, hierarchyId).replace(NODE_ID, nodeId),
                hierarchyNodeChildrenModel, HierarchyNodeRelationshipChildrenModel.class, context);
    }

    @Override
    public HierarchyNodeProductModel createRelationshipNodeAndProduct(
            final HierarchyNodeProductModel hierarchyNodeProductModel, final String hierarchyId, final String nodeId,
            final ApiContext context) {
        return executePostRequest(CREATE_RELATIONSHIP_NODE_AND_PRODUCT.replace(HIERARCHY_ID, hierarchyId).replace(NODE_ID, nodeId),
                hierarchyNodeProductModel, HierarchyNodeProductModel.class, context);
    }

    @Override
    public void deleteNodeParentRelationship(final String hierarchyId, final String nodeId, final ApiContext context) {
        executeDeleteRequest(DELETE_NODE_PARENT_RELATIONSHIP.replace(HIERARCHY_ID, hierarchyId)
                .replace(NODE_ID, nodeId), context);
    }

    @Override
    public HierarchyNodeModel deleteRelationshipNodeAndProduct(
            final HierarchyNodeProductModel hierarchyNodeProductModel, final String hierarchyId, final String nodeId,
            final ApiContext context) {
        return executeDeleteWithPayloadRequest(DELETE_RELATIONSHIP_NODE_AND_PRODUCT.replace(HIERARCHY_ID, hierarchyId)
                .replace(NODE_ID, nodeId), hierarchyNodeProductModel, HierarchyNodeModel.class, context);
    }

    @Override
    public void updateNodeParent(
            final HierarchyNodeRelationshipChildrenModel hierarchyNodeRelationshipChildrenModel, final String hierarchyId,
            final String nodeId, final ApiContext context) {

        executePutWithoutReturnRequest(UPDATE_NODE_PARENT_RELATIONSHIP.replace(HIERARCHY_ID, hierarchyId)
                .replace(NODE_ID, nodeId), hierarchyNodeRelationshipChildrenModel, context);
    }
}
