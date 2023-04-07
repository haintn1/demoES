package digi.ecomm.platformservice.persistent.interceptor;

import digi.ecomm.entity.AbstractEntity;

public interface DeleteInterceptor<ENTITY extends AbstractEntity> extends Interceptor { //NOSONAR
    /**
     * Called before an object is deleted. It is not recommended that the interceptor modify the <tt>state</tt>.
     *
     * @param entity  The entity instance being deleted
     * @param context
     * @throws InterceptorException Thrown if the interceptor encounters any problems handling the callback.
     */
    void onDelete(ENTITY entity, InterceptorContext context) throws InterceptorException;
}
