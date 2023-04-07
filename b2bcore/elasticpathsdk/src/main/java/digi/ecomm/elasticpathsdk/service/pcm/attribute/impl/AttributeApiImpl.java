package digi.ecomm.elasticpathsdk.service.pcm.attribute.impl;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.request.attribute.AttributeRequest;
import digi.ecomm.elasticpathsdk.response.attribute.AttributeResponse;
import digi.ecomm.elasticpathsdk.service.AbstractService;
import digi.ecomm.elasticpathsdk.service.pcm.attribute.AttributeApi;
import org.apache.http.client.config.RequestConfig;

public class AttributeApiImpl extends AbstractService implements AttributeApi {
    private static final String ID = ":id";
    private static final String BASE_ATTRIBUTE_URL = "/v2/fields";
    private static final String ATTRIBUTE_URL = BASE_ATTRIBUTE_URL + "/:id";

    public AttributeApiImpl() {
    }

    public AttributeApiImpl(final int connectTimeout, final int socketTimeout) {
        super(connectTimeout, socketTimeout);
    }

    public AttributeApiImpl(final RequestConfig requestConfig) {
        super(requestConfig);
    }

    @Override
    public AttributeResponse create(final AttributeRequest payload, final ApiContext context) {
        return executePostRequest(BASE_ATTRIBUTE_URL, payload, AttributeResponse.class, context);
    }

    @Override
    public AttributeResponse update(final AttributeRequest payload, final String id, final ApiContext context) {
        return executePutRequest(ATTRIBUTE_URL.replace(ID, id), payload, AttributeResponse.class, context);
    }

    @Override
    public void delete(final String id, final ApiContext context) {
        executeDeleteRequest(ATTRIBUTE_URL.replace(ID, id), context);
    }
}
