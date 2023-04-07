package digi.ecomm.platformservice.persistent.interceptor;

import digi.ecomm.entity.AbstractEntity;

public interface PrepareInterceptor<ENTITY extends AbstractEntity> extends Interceptor<ENTITY> { //NOSONAR
    /**
     * Called before an object is saved, before {@link ValidateInterceptor}.
     * The interceptor may modify the <tt>state</tt>, which will be used for
     * the SQL <tt>INSERT</tt> and propagated to the persistent object.
     *
     * @param entity  The entity instance whose state is being inserted
     * @param context
     * @return <tt>true</tt> if the user modified the <tt>state</tt> in any way.
     * @throws InterceptorException Thrown if the interceptor encounters any problems handling the callback.
     */
    boolean onPrepare(ENTITY entity, InterceptorContext context) throws InterceptorException;
}
