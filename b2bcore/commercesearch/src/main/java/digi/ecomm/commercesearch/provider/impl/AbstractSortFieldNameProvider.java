package digi.ecomm.commercesearch.provider.impl;

import digi.ecomm.commercesearch.provider.SortFieldNameProvider;
import digi.ecomm.platformservice.persistent.service.EntityService;

import javax.annotation.Resource;

public abstract class AbstractSortFieldNameProvider implements SortFieldNameProvider {
    @Resource
    private EntityService entityService;

    public EntityService getEntityService() {
        return entityService;
    }
}
