package digi.ecomm.elasticpathsdk.service.pcm.variation.impl;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.variation.CreateVariationModel;
import digi.ecomm.elasticpathsdk.model.variation.UpdateVariationModel;
import digi.ecomm.elasticpathsdk.model.variation.VariationsModel;
import digi.ecomm.elasticpathsdk.service.AbstractService;
import digi.ecomm.elasticpathsdk.service.pcm.variation.VariationApi;
import org.apache.http.client.config.RequestConfig;

public class VariationApiImpl extends AbstractService implements VariationApi {
    private static final String VARIATION_ID = ":variationId";
    private static final String VARIATIONS_BASE_URL = "/pcm/variations";
    private static final String VARIATION_URL = "/pcm/variations/:variationId";

    public VariationApiImpl() {
    }

    public VariationApiImpl(final int connectTimeout, final int socketTimeout) {
        super(connectTimeout, socketTimeout);
    }

    public VariationApiImpl(final RequestConfig requestConfig) {
        super(requestConfig);
    }

    @Override
    public VariationsModel getAll(final ApiContext context) {
        return executeGetRequest(VARIATIONS_BASE_URL, VariationsModel.class, context);
    }

    @Override
    public CreateVariationModel create(final CreateVariationModel payload, final ApiContext context) {
        return executePostRequest(VARIATIONS_BASE_URL, payload, CreateVariationModel.class, context);
    }

    @Override
    public void update(final String id, final UpdateVariationModel payload, final ApiContext context) {
        executePutWithoutReturnRequest(VARIATION_URL.replace(VARIATION_ID, id), payload, context);
    }

    @Override
    public void delete(final String id, final ApiContext context) {
        executeDeleteRequest(VARIATION_URL.replace(VARIATION_ID, id), context);
    }
}
