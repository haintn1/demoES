package digi.ecomm.elasticpathpcm.sync.push.strategy;

import digi.ecomm.elasticpathpcm.api.ApiContextAware;
import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.exception.ApiCommunicationException;
import digi.ecomm.elasticpathsdk.model.variation.*;
import digi.ecomm.elasticpathsdk.service.pcm.variation.VariationApi;
import digi.ecomm.elasticpathsdk.service.pcm.variation.impl.VariationApiImpl;
import digi.ecomm.entity.pcm.Variation;
import digi.ecomm.pcm.sync.push.exception.UntrackedDataDeletionException;
import digi.ecomm.pcm.sync.push.PushResult;
import digi.ecomm.pcm.sync.push.strategy.VariationPushStrategy;
import org.apache.http.client.config.RequestConfig;

public class EpVariationPushStrategy implements VariationPushStrategy, ApiContextAware {
    private final VariationApi variationApi;
    private ApiContext context;

    public EpVariationPushStrategy(final RequestConfig requestConfig) {
        this.variationApi = new VariationApiImpl(requestConfig);
    }

    @Override
    public void setApiContext(final ApiContext context) {
        this.context = context;
    }

    @InjectApiContext
    @Override
    public PushResult create(final Variation variation) {
        final VariationAttributesModel attributes = new VariationAttributesModel();
        attributes.setName(variation.getName());
        final CreateVariationDataModel data = new CreateVariationDataModel();
        data.setAttributes(attributes);
        final CreateVariationModel payload = new CreateVariationModel();
        payload.setData(data);
        PushResult result;
        try {
            final CreateVariationModel variationResponse = variationApi.create(payload, context);
            result = PushResult.success(variationResponse.getData().getId());
        } catch (ApiCommunicationException e) {
            result = PushResult.failed(e.getMessage());
        }
        return result;
    }

    @InjectApiContext
    @Override
    public PushResult update(final Variation variation) {
        if (!variation.isTracked()) {
            return create(variation);
        }

        final VariationAttributesModel attributes = new VariationAttributesModel();
        attributes.setName(variation.getName());
        final UpdateVariationDataModel data = new UpdateVariationDataModel();
        data.setId(variation.getExternalId());
        data.setAttributes(attributes);
        final UpdateVariationModel payload = new UpdateVariationModel();
        payload.setData(data);
        PushResult result;
        try {
            variationApi.update(variation.getExternalId(), payload, context);
            result = PushResult.success(variation.getExternalId());
        } catch (ApiCommunicationException e) {
            result = PushResult.failed(e.getMessage());
        }
        return result;
    }

    @InjectApiContext
    @Override
    public PushResult delete(final Variation variation) {
        PushResult result;
        if (variation.isTracked()) {
            try {
                variationApi.delete(variation.getExternalId(), context);
                result = PushResult.success(variation.getExternalId());
            } catch (ApiCommunicationException e) {
                result = PushResult.failed(e.getMessage());
            }
        } else {
            throw new UntrackedDataDeletionException(String.format("Variation % is not tracked by third party", variation));
        }
        return result;
    }
}
