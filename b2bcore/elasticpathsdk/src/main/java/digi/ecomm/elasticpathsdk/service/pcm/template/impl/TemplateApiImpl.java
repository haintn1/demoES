package digi.ecomm.elasticpathsdk.service.pcm.template.impl;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.request.template.TemplateRequest;
import digi.ecomm.elasticpathsdk.response.template.TemplateResponse;
import digi.ecomm.elasticpathsdk.service.AbstractService;
import digi.ecomm.elasticpathsdk.service.pcm.template.TemplateApi;
import org.apache.http.client.config.RequestConfig;

public class TemplateApiImpl extends AbstractService implements TemplateApi {
    private static final String ID = ":id";
    private static final String BASE_TEMPLATE_URL = "/v2/flows";
    private static final String TEMPLATE_URL = BASE_TEMPLATE_URL + ":id";

    public TemplateApiImpl() {
    }

    public TemplateApiImpl(final int connectTimeout, final int socketTimeout) {
        super(connectTimeout, socketTimeout);
    }

    public TemplateApiImpl(final RequestConfig requestConfig) {
        super(requestConfig);
    }

    @Override
    public TemplateResponse create(final TemplateRequest payload, final ApiContext context) {
        return executePostRequest(BASE_TEMPLATE_URL, payload, TemplateResponse.class, context);
    }

    @Override
    public TemplateResponse update(final String id, final TemplateRequest payload, final ApiContext context) {
        return executePutRequest(TEMPLATE_URL.replace(ID, id), payload, TemplateResponse.class, context);
    }

    @Override
    public void delete(final String id, final ApiContext context) {
        executeDeleteRequest(TEMPLATE_URL.replace(ID, id), context);
    }

    @Override
    public TemplateResponse getById(final String id, final ApiContext context) {
        return executeGetRequest(TEMPLATE_URL.replace(ID, id), TemplateResponse.class, context);
    }
}
