package digi.ecomm.elasticpathsdk.service.pcm.hierarchy;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.hierarchy.HierarchiesModel;
import digi.ecomm.elasticpathsdk.model.hierarchy.HierarchyModel;


public interface HierarchyApi {

    /**
     * Get all hierarchies in the latest release.
     *
     * @param catalogId
     * @param context
     * @return
     */
    HierarchiesModel getAllHierarchiesInTheLatestRelease(String catalogId, ApiContext context);

    /**
     * Get all hierarchies.
     *
     * @param context
     * @return
     */
    HierarchiesModel getAllHierarchies(ApiContext context);

    /**
     * Get a hierarchy.
     *
     * @param hierarchyId
     * @param context
     * @return
     */
    HierarchyModel getHierarchy(String hierarchyId, ApiContext context);

    /**
     * Create a hierarchy.
     *
     * @param hierarchyModel
     * @param context
     * @return
     */
    HierarchyModel createHierarchy(HierarchyModel hierarchyModel, ApiContext context);

    /**
     * Update a hierarchy.
     *
     * @param hierarchyModel
     * @param hierarchyId
     * @param context
     * @return
     */
    HierarchyModel updateHierarchy(HierarchyModel hierarchyModel, String hierarchyId, ApiContext context);

    /**
     * Delete a hierarchy.
     *
     * @param hierarchyId
     * @param context
     */
    void deleteHierarchy(String hierarchyId, ApiContext context);
}
