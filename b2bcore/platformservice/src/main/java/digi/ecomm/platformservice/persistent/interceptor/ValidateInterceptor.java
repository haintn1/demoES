package digi.ecomm.platformservice.persistent.interceptor;

import digi.ecomm.entity.AbstractEntity;

public interface ValidateInterceptor<ENTITY extends AbstractEntity> extends Interceptor { //NOSONAR
    /**
     * Called before an object is saved, after {@link PrepareInterceptor}.
     * The interceptor may modify the <tt>state</tt>, which will be used for
     * the SQL <tt>INSERT</tt> and propagated to the persistent object.
     *
     * @param entity  The entity instance whose state is being inserted
     * @param context
     * @throws InterceptorException Thrown if the interceptor encounters any problems handling the callback.
     */
    void onValidate(ENTITY entity, InterceptorContext context) throws InterceptorException;
}
