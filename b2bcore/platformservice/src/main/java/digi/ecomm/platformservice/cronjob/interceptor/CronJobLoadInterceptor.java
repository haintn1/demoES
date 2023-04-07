package digi.ecomm.platformservice.cronjob.interceptor;

import digi.ecomm.entity.cronjob.CronJob;
import digi.ecomm.platformservice.persistent.interceptor.InterceptorContext;
import digi.ecomm.platformservice.persistent.interceptor.InterceptorException;
import digi.ecomm.platformservice.persistent.interceptor.LoadInterceptor;
import digi.ecomm.platformservice.persistent.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class CronJobLoadInterceptor implements LoadInterceptor<CronJob> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronJobLoadInterceptor.class);

    @Resource
    private EntityService entityService;

    @Override
    public boolean onLoad(final CronJob entity, final InterceptorContext context) throws InterceptorException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("This is a test message from a sample interceptor!");
        }
        return false;
    }

    @Override
    public Class<CronJob> targetType() {
        return CronJob.class;
    }
}
