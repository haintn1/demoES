package digi.ecomm.elasticpathpcm.sync.fetch.strategy;

import digi.ecomm.elasticpathpcm.api.ApiContextAware;
import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.variation.VariationOptionsModel;
import digi.ecomm.elasticpathsdk.service.pcm.variation.VariationOptionApi;
import digi.ecomm.elasticpathsdk.service.pcm.variation.impl.VariationOptionApiImpl;
import digi.ecomm.entity.pcm.Variation;
import digi.ecomm.entity.pcm.VariationOption;
import digi.ecomm.pcm.service.VariationService;
import digi.ecomm.pcm.sync.fetch.strategy.AbstractFetchStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.client.config.RequestConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EpVariationOptionFetchStrategy extends AbstractFetchStrategy<VariationOption> implements ApiContextAware {
    private final VariationService variationService;
    private ApiContext context;
    private final VariationOptionApi variationOptionApi;

    public EpVariationOptionFetchStrategy(final VariationService variationService, final RequestConfig requestConfig) {
        this.variationService = variationService;
        this.variationOptionApi = new VariationOptionApiImpl(requestConfig);
    }

    /**
     * Fetch variation options from EP.
     *
     * @return
     */
    @InjectApiContext
    public List<VariationOption> fetch(final List<Variation> variations) {
        final List<VariationOption> variationOptions = new ArrayList<>();
        variations.forEach(variation -> {
            final VariationOptionsModel variationOptionsModel =
                    variationOptionApi.getAllByVariation(variation.getExternalId(), context);
            if (CollectionUtils.isNotEmpty(variationOptionsModel.getData())) {
                variationOptions.addAll(variationOptionsModel.getData().stream()
                        .map(variationOptionData -> {
                            final VariationOption variationOption = new VariationOption();
                            variationOption.setExternalId(variationOptionData.getId());
                            final String optionName = variationOptionData.getAttributes().getName();
                            variationOption.setCode(optionName);
                            variationOption.setName(optionName);
                            variationOption.setDescription(variationOptionData.getAttributes().getDescription());
                            variationOption.setVariation(variation);
                            return variationOption;
                        }).collect(Collectors.toList()));
            }
        });
        return processInternal(variationOptions);
    }

    @Override
    protected List<VariationOption> getEntitiesByExternalIds(final List<String> externalIds) {
        return variationService.getAllOptionsByExternalIds(externalIds);
    }

    @Override
    protected void copyProperties(final VariationOption source, final VariationOption target) {
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
    }

    @Override
    public void setApiContext(final ApiContext context) {
        this.context = context;
    }
}
