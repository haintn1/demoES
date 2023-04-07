package digi.ecomm.commercesearch.provider.impl;

import digi.ecomm.commercesearch.provider.FieldNameProvider;
import digi.ecomm.platformservice.persistent.service.EntityService;

import javax.annotation.Resource;

public abstract class AbstractFieldNameProvider implements FieldNameProvider {
    @Resource
    private EntityService entityService;

    public EntityService getEntityService() {
        return entityService;
    }
}
