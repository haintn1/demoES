package digi.ecomm.pcm.sync.push.handler;

import digi.ecomm.entity.pcm.Variation;
import digi.ecomm.pcm.sync.push.PushResult;
import digi.ecomm.pcm.sync.push.strategy.VariationPushStrategy;
import digi.ecomm.platformservice.persistent.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import javax.annotation.Resource;

@RepositoryEventHandler
public class VariationEventHandler extends AbstractEventHandler<Variation> {

    private static final Logger LOGGER = LoggerFactory.getLogger(VariationEventHandler.class);

    @Resource
    private EntityService entityService;

    @Resource
    private VariationPushStrategy variationStrategy;

    /**
     * Handle POST.
     *
     * @param variation
     */
    @HandleAfterCreate
    public void handleVariationCreate(final Variation variation) {
        if (!variation.isStopSync()) {
            final PushResult result = variationStrategy.create(variation);
            postCreate(variation, result);
        } else {
            logging(variation);
        }
    }

    /**
     * Handle PUT.
     *
     * @param variation
     */
    @HandleAfterSave
    public void handleVariationUpdate(final Variation variation) {
        entityService.refresh(variation);
        if (!variation.isStopSync()) {
            final PushResult result = variationStrategy.update(variation);
            postUpdate(variation, result);
        } else {
            logging(variation);
        }
    }

    /**
     * Handle DELETE.
     *
     * @param variation
     */
    @HandleAfterDelete
    public void handleVariationDelete(final Variation variation) {
        if (!variation.isStopSync()) {
            final PushResult result = variationStrategy.delete(variation);
            postDelete(variation, result);
        } else {
            logging(variation);
        }
    }

    private void logging(final Variation variation) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Variation {} is ignored from sync.", variation);
        }
    }
}
