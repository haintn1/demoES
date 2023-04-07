package digi.ecomm.elasticpathpcm.api;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class ApiContextAspect {

    @Resource
    private ApiContextFactory apiContextFactory;

    /**
     * Inject EP API context.
     *
     * @param joinPoint
     */
    @Before(value = "@annotation(digi.ecomm.elasticpathpcm.api.ApiContextAware.InjectApiContext)")
    public void setApiContext(final JoinPoint joinPoint) {
        final Object currentObject = joinPoint.getThis();
        if (!(currentObject instanceof ApiContextAware)) {
            throw new IllegalArgumentException(String.format("%s must be an instance of %s",
                    currentObject, ApiContextAware.class.getName()));
        }
        ((ApiContextAware) currentObject).setApiContext(apiContextFactory.get());
    }
}
