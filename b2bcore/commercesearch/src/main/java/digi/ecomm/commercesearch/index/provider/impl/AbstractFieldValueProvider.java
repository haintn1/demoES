package digi.ecomm.commercesearch.index.provider.impl;

import digi.ecomm.platformservice.persistent.service.EntityService;

import javax.annotation.Resource;

public abstract class AbstractFieldValueProvider {

    @Resource
    private EntityService entityService;

    public EntityService getEntityService() {
        return entityService;
    }
}
