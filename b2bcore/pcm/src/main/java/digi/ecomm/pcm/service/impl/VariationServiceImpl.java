package digi.ecomm.pcm.service.impl;

import digi.ecomm.entity.pcm.Variation;
import digi.ecomm.entity.pcm.VariationOption;
import digi.ecomm.pcm.repository.VariationOptionRepository;
import digi.ecomm.pcm.repository.VariationRepository;
import digi.ecomm.pcm.service.VariationService;
import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class VariationServiceImpl implements VariationService {

    private final VariationRepository variationRepository;
    private final VariationOptionRepository variationOptionRepository;

    @Override
    public List<Variation> getAllByExternalIds(final Collection<String> externalIds) {
        ServicesUtils.validateParameterNotEmptyStandardMessage("externalIds", externalIds);

        return variationRepository.findByExternalIds(externalIds);
    }

    @Override
    public List<VariationOption> getAllOptionsByExternalIds(final Collection<String> externalIds) {
        ServicesUtils.validateParameterNotEmptyStandardMessage("externalIds", externalIds);

        return variationOptionRepository.findByExternalIds(externalIds);
    }
}
