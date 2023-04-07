package digi.ecomm.platformservice.persistent.interceptor;

import digi.ecomm.entity.AbstractEntity;

public interface Interceptor<ENTITY extends AbstractEntity> { //NOSONAR

    /**
     * Entity type to be intercepted.
     *
     * @return
     */
    Class<ENTITY> targetType();
}
