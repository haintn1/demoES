package digi.ecomm.elasticpathpcm.api;

import digi.ecomm.elasticpathsdk.context.ApiContext;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public interface ApiContextAware {

    /**
     * Set API context.
     *
     * @param context
     */
    void setApiContext(ApiContext context);

    @Target(METHOD)
    @Retention(RUNTIME)
    @interface InjectApiContext {

    }
}
