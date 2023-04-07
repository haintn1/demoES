package digi.ecomm.pcm.service;

import digi.ecomm.entity.pcm.Variation;
import digi.ecomm.entity.pcm.VariationOption;

import java.util.Collection;
import java.util.List;

public interface VariationService {

    /**
     * Get all variations by external ids.
     *
     * @param externalIds
     * @return
     */
    List<Variation> getAllByExternalIds(Collection<String> externalIds);

    /**
     * Get all variation options by external ids.
     *
     * @param externalIds
     * @return
     */
    List<VariationOption> getAllOptionsByExternalIds(Collection<String> externalIds);
}
