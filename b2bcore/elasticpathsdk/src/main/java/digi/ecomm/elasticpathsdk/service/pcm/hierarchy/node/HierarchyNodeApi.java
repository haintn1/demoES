package digi.ecomm.elasticpathsdk.service.pcm.hierarchy.node;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.hierarchy.node.HierarchyNodeModel;


public interface HierarchyNodeApi {

    /**
     * Get a hierarchy node.
     *
     * @param hierarchyId
     * @param nodeId
     * @param context
     * @return
     */
    HierarchyNodeModel getHierarchyNode(String hierarchyId, String nodeId, ApiContext context);

    /**
     * Create a hierarchy node.
     *
     * @param hierarchyNodeModel
     * @param hierarchyId
     * @param context
     * @return
     */
    HierarchyNodeModel createHierarchyNode(HierarchyNodeModel hierarchyNodeModel, String hierarchyId, ApiContext context);

    /**
     * Update a hierarchy node.
     *
     * @param hierarchyNodeModel
     * @param hierarchyId
     * @param nodeId
     * @param context
     * @return
     */
    HierarchyNodeModel updateHierarchyNode(HierarchyNodeModel hierarchyNodeModel, String hierarchyId, String nodeId, ApiContext context);

    /**
     * Delete a hierarchy node.
     *
     * @param hierarchyId
     * @param nodeId
     * @param context
     */
    void deleteHierarchyNode(String hierarchyId, String nodeId, ApiContext context);
}
