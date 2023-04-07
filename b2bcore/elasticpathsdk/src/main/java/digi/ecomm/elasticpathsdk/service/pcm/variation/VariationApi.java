package digi.ecomm.elasticpathsdk.service.pcm.variation;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.variation.CreateVariationModel;
import digi.ecomm.elasticpathsdk.model.variation.UpdateVariationModel;
import digi.ecomm.elasticpathsdk.model.variation.VariationsModel;

public interface VariationApi {

    /**
     * Get all variations.
     *
     * @param context
     * @return
     */
    VariationsModel getAll(ApiContext context);

    /**
     * Create a variation.
     *
     * @param payload
     * @param context
     * @return
     */
    CreateVariationModel create(CreateVariationModel payload, ApiContext context);

    /**
     * Update a variation.
     *
     * @param id
     * @param payload
     * @param context
     * @return
     */
    void update(String id, UpdateVariationModel payload, ApiContext context);

    /**
     * Delete a variation.
     *
     * @param id
     * @return
     */
    void delete(String id, ApiContext context);
}
