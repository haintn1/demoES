package digi.ecomm.elasticpathsdk.service.pcm.variation.impl;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.variation.VariationOptionsModel;
import digi.ecomm.elasticpathsdk.service.AbstractService;
import digi.ecomm.elasticpathsdk.service.pcm.variation.VariationOptionApi;
import org.apache.http.client.config.RequestConfig;

public class VariationOptionApiImpl extends AbstractService implements VariationOptionApi {
    private static final String VARIATION_ID = ":variationId";
    private static final String GET_ALL_OPTIONS_URL = "/pcm/variations/:variationId/options";

    public VariationOptionApiImpl() {
    }

    public VariationOptionApiImpl(final int connectTimeout, final int socketTimeout) {
        super(connectTimeout, socketTimeout);
    }

    public VariationOptionApiImpl(final RequestConfig requestConfig) {
        super(requestConfig);
    }

    @Override
    public VariationOptionsModel getAllByVariation(final String variationId, final ApiContext context) {
        return executeGetRequest(GET_ALL_OPTIONS_URL.replace(VARIATION_ID, variationId),
                VariationOptionsModel.class, context);
    }
}
