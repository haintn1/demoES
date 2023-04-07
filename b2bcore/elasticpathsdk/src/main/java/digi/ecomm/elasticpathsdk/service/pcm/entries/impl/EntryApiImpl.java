package digi.ecomm.elasticpathsdk.service.pcm.entries.impl;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.request.entries.EntryRequest;
import digi.ecomm.elasticpathsdk.response.entries.EntryResponse;
import digi.ecomm.elasticpathsdk.service.AbstractService;
import digi.ecomm.elasticpathsdk.service.pcm.entries.EntryApi;
import org.apache.http.client.config.RequestConfig;

public class EntryApiImpl extends AbstractService implements EntryApi {
    private static final String ID = ":id";
    private static final String BASE_ENTRY_URL = "/pcm/flows/:slug/entries";
    private static final String ENTRY_URL = BASE_ENTRY_URL + "/:id";

    public EntryApiImpl() {
    }

    public EntryApiImpl(final int connectTimeout, final int socketTimeout) {
        super(connectTimeout, socketTimeout);
    }

    public EntryApiImpl(final RequestConfig requestConfig) {
        super(requestConfig);
    }

    @Override
    public EntryResponse create(final EntryRequest payload, final String slug, final ApiContext context) {
        return executePostRequest(BASE_ENTRY_URL.replace("slug", slug), payload, EntryResponse.class, context);
    }

    @Override
    public EntryResponse update(final EntryRequest payload, final String id, final ApiContext context) {
        return executePutRequest(ENTRY_URL.replace(ID, id), payload, EntryResponse.class, context);
    }

    @Override
    public void delete(final String id, final String slug, final ApiContext context) {
        executeDeleteRequest(ENTRY_URL.replace("slug", slug).replace(ID, id), context);
    }
}
