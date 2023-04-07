package digi.ecomm.elasticpathsdk.service.pcm.attribute;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.request.attribute.AttributeRequest;
import digi.ecomm.elasticpathsdk.response.attribute.AttributeResponse;

public interface AttributeApi {

    /**
     * Create an attribute.
     *
     * @param payload
     * @param context
     * @return
     */
    AttributeResponse create(AttributeRequest payload, ApiContext context);

    /**
     * Update an attribute.
     *
     * @param payload
     * @param id
     * @param context
     * @return
     */
    AttributeResponse update(AttributeRequest payload, String id, ApiContext context);

    /**
     * Delete an attribute.
     *
     * @param id
     * @param context
     */
    void delete(String id, ApiContext context);
}
