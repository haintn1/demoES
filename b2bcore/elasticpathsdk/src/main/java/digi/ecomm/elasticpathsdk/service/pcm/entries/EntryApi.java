package digi.ecomm.elasticpathsdk.service.pcm.entries;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.request.entries.EntryRequest;
import digi.ecomm.elasticpathsdk.response.entries.EntryResponse;

public interface EntryApi {

    /**
     * Create attribute entry.
     *
     * @param request
     * @param slug
     * @param context
     * @return
     */
    EntryResponse create(EntryRequest request, String slug, ApiContext context);

    /**
     * Update attribute entry.
     *
     * @param request
     * @param id
     * @param context
     * @return
     */
    EntryResponse update(EntryRequest request, String id, ApiContext context);

    /**
     * Delete attribute entry.
     *
     * @param id
     * @param slug
     * @param context
     */
    void delete(String id, String slug, ApiContext context);
}
