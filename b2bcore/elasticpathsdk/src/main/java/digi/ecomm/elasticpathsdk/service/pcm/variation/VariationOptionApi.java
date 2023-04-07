package digi.ecomm.elasticpathsdk.service.pcm.variation;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.variation.VariationOptionsModel;

public interface VariationOptionApi {

    /**
     * Get all variation options by variation id.
     *
     * @param variationId
     * @param context
     * @return
     */
    VariationOptionsModel getAllByVariation(String variationId, ApiContext context);
}
