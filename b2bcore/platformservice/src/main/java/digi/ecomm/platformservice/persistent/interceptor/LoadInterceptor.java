package digi.ecomm.platformservice.persistent.interceptor;

import digi.ecomm.entity.AbstractEntity;

public interface LoadInterceptor<ENTITY extends AbstractEntity> extends Interceptor<ENTITY> { //NOSONAR
    /**
     * Called just before an object is initialized. The interceptor may change the <tt>state</tt>, which will
     * be propagated to the persistent object. Note that when this method is called, <tt>entity</tt> will be
     * an empty uninitialized instance of the class.
     * <p/>
     * NOTE: The indexes across the <tt>state</tt>, <tt>propertyNames</tt> and <tt>types</tt> arrays match.
     *
     * @param entity  The entity instance being loaded
     * @param context
     * @return {@code true} if the user modified the <tt>state</tt> in any way.
     * @throws InterceptorException Thrown if the interceptor encounters any problems handling the callback.
     */
    boolean onLoad(ENTITY entity, InterceptorContext context) throws InterceptorException;
}
