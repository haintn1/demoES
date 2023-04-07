package digi.ecomm.elasticpathpcm.sync.fetch.strategy;

import digi.ecomm.elasticpathpcm.api.ApiContextAware;
import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.variation.VariationsModel;
import digi.ecomm.elasticpathsdk.service.pcm.variation.VariationApi;
import digi.ecomm.elasticpathsdk.service.pcm.variation.impl.VariationApiImpl;
import digi.ecomm.entity.pcm.Variation;
import digi.ecomm.pcm.service.VariationService;
import digi.ecomm.pcm.sync.fetch.strategy.AbstractFetchStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.client.config.RequestConfig;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EpVariationFetchStrategy extends AbstractFetchStrategy<Variation> implements ApiContextAware {
    private final VariationService variationService;
    private final VariationApi variationApi;
    private ApiContext context;

    public EpVariationFetchStrategy(final VariationService variationService, final RequestConfig requestConfig) {
        this.variationService = variationService;
        this.variationApi = new VariationApiImpl(requestConfig);
    }

    /**
     * Fetch variations from EP.
     *
     * @return
     */
    @InjectApiContext
    public List<Variation> fetch() {
        final VariationsModel variationsModel = variationApi.getAll(context);
        if (CollectionUtils.isNotEmpty(variationsModel.getData())) {
            final List<Variation> results = variationsModel.getData().stream()
                    .map(variationData -> {
                        final Variation variation = new Variation();
                        variation.setExternalId(variationData.getId());
                        final String variationName = variationData.getAttributes().getName();
                        variation.setCode(variationName);
                        variation.setName(variationName);
                        variation.setDescription(variationName);
                        return variation;
                    }).collect(Collectors.toList());
            return processInternal(results);
        }
        return Collections.emptyList();
    }

    @Override
    protected List<Variation> getEntitiesByExternalIds(final List<String> externalIds) {
        return variationService.getAllByExternalIds(externalIds);
    }

    @Override
    protected void copyProperties(final Variation source, final Variation target) {
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
    }

    @Override
    public void setApiContext(final ApiContext context) {
        this.context = context;
    }
}
