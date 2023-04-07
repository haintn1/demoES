package digi.ecomm.elasticpathsdk.service.pcm.template;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.request.template.TemplateRequest;
import digi.ecomm.elasticpathsdk.response.template.TemplateResponse;

public interface TemplateApi {

    /**
     * Create a template.
     *
     * @param request
     * @param context
     * @return
     */
    TemplateResponse create(TemplateRequest request, ApiContext context);

    /**
     * Update a template.
     *
     * @param id
     * @param payload
     * @param context
     * @return
     */
    TemplateResponse update(String id, TemplateRequest payload, ApiContext context);

    /**
     * Delete a template.
     *
     * @param id
     * @param context
     */
    void delete(String id, ApiContext context);

    /**
     * Get a template.
     *
     * @param id
     * @param context
     * @return
     */
    TemplateResponse getById(String id, ApiContext context);
}
