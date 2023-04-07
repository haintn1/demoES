package digi.ecomm.pcm.sync.push.strategy;

import digi.ecomm.entity.pcm.Variation;
import digi.ecomm.pcm.sync.push.PushResult;

public interface VariationPushStrategy {

    /**
     * Create variation in third party.
     *
     * @param variation
     */
    PushResult create(Variation variation);

    /**
     * Update variation in third party.
     *
     * @param variation
     */
    PushResult update(Variation variation);

    /**
     * Delete variation in third party.
     *
     * @param variation
     */
    PushResult delete(Variation variation);
}
