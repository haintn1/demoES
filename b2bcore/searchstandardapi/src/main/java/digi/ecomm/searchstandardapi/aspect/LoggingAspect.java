package digi.ecomm.searchstandardapi.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Calculate response time of all APIs.
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    public Object profileApiMethods(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        final String className = methodSignature.getDeclaringType().getName();
        final String methodName = methodSignature.getName();
        final String[] parameterNames = methodSignature.getParameterNames();

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Execution time of {}.{}({}) :: {} ms",
                    className, methodName, StringUtils.join(parameterNames, ","), stopWatch.getTotalTimeMillis());
        }
        return result;
    }
}
