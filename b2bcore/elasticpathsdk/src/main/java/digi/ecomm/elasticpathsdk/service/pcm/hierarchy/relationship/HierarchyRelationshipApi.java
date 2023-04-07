package digi.ecomm.elasticpathsdk.service.pcm.hierarchy.relationship;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.hierarchy.node.HierarchyNodeModel;
import digi.ecomm.elasticpathsdk.model.hierarchy.relationship.HierarchyNodeChildrenModel;
import digi.ecomm.elasticpathsdk.model.hierarchy.relationship.HierarchyNodeProductModel;
import digi.ecomm.elasticpathsdk.model.hierarchy.relationship.HierarchyNodeRelationshipChildrenModel;


public interface HierarchyRelationshipApi {

    /**
     * Get node's children.
     *
     * @param hierarchyId
     * @param nodeId
     * @param context
     * @return
     */
    HierarchyNodeChildrenModel getNodeChildren(String hierarchyId, String nodeId, ApiContext context);

    /**
     * Get node's products.
     *
     * @param hierarchyId
     * @param nodeId
     * @param context
     * @return
     */
    HierarchyNodeProductModel getNodeProducts(String hierarchyId, String nodeId, ApiContext context);

    /**
     * Create node child relationship.
     *
     * @param hierarchyNodeChildrenModel
     * @param hierarchyId
     * @param nodeId
     * @param context
     * @return
     */
    HierarchyNodeRelationshipChildrenModel createRelationshipNodeAndChild(
            HierarchyNodeChildrenModel hierarchyNodeChildrenModel, String hierarchyId, String nodeId, ApiContext context);

    /**
     * Create product node relationship.
     *
     * @param hierarchyNodeProductModel
     * @param hierarchyId
     * @param nodeId
     * @param context
     * @return
     */
    HierarchyNodeProductModel createRelationshipNodeAndProduct(
            HierarchyNodeProductModel hierarchyNodeProductModel, String hierarchyId, String nodeId, ApiContext context);

    /**
     * Delete node parent relationship.
     *
     * @param hierarchyId
     * @param nodeId
     * @param context
     */
    void deleteNodeParentRelationship(String hierarchyId, String nodeId, ApiContext context);

    /**
     * Delete product node relationship.
     *
     * @param hierarchyNodeProductModel
     * @param hierarchyId
     * @param nodeId
     * @param context
     * @return
     */
    HierarchyNodeModel deleteRelationshipNodeAndProduct(
            HierarchyNodeProductModel hierarchyNodeProductModel, String hierarchyId, String nodeId, ApiContext context);

    /**
     * Update child relationship.
     *
     * @param hierarchyNodeRelationshipChildrenModel
     * @param hierarchyId
     * @param nodeId
     * @param context
     */
    void updateNodeParent(
            HierarchyNodeRelationshipChildrenModel hierarchyNodeRelationshipChildrenModel, String hierarchyId,
            String nodeId, ApiContext context);
}
