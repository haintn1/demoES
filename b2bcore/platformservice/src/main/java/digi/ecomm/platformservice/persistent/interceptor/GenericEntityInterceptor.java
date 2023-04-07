package digi.ecomm.platformservice.persistent.interceptor;

import digi.ecomm.entity.AbstractEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ClassUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class GenericEntityInterceptor extends EmptyInterceptor implements ApplicationContextAware {

    private static final long serialVersionUID = 1L;

    public static final String INTERCEPTOR_MAPPING_BEAN_NAME = "interceptorMapping";

    private transient ApplicationContext applicationContext;
    private transient Map<Class<? extends AbstractEntity>, List<Interceptor>> interceptorMapping; //NOSONAR

    @Override
    public boolean onLoad(final Object entity, final Serializable id, final Object[] state,
                          final String[] propertyNames, final Type[] types) {
        final List<LoadInterceptor> interceptors = getInterceptors(entity, LoadInterceptor.class); //NOSONAR
        boolean stateModified = false;
        final InterceptorContext context = createInterceptorContext(id, state, propertyNames, types);
        for (LoadInterceptor interceptor : interceptors) { //NOSONAR
            if (interceptor.onLoad((AbstractEntity) entity, context)) {
                stateModified = true;
            }
        }
        return stateModified;
    }

    @Override
    public boolean onSave(final Object entity, final Serializable id, final Object[] state,
                          final String[] propertyNames, final Type[] types) {
        boolean stateModified = false;
        final InterceptorContext context = createInterceptorContext(id, state, propertyNames, types);

        final List<PrepareInterceptor> prepareInterceptors = getInterceptors(entity, PrepareInterceptor.class); //NOSONAR
        for (PrepareInterceptor interceptor : prepareInterceptors) { //NOSONAR
            if (interceptor.onPrepare((AbstractEntity) entity, context)) {
                stateModified = true;
            }
        }

        final List<ValidateInterceptor> validateInterceptors = getInterceptors(entity, ValidateInterceptor.class); //NOSONAR
        validateInterceptors.forEach(interceptor -> interceptor.onValidate((AbstractEntity) entity, context));

        return stateModified;
    }

    @Override
    public void onDelete(final Object entity, final Serializable id, final Object[] state,
                         final String[] propertyNames, final Type[] types) {
        final List<DeleteInterceptor> interceptors = getInterceptors(entity, DeleteInterceptor.class); //NOSONAR
        final InterceptorContext context = createInterceptorContext(id, state, propertyNames, types);
        interceptors.forEach(interceptor -> interceptor.onDelete((AbstractEntity) entity, context));
    }

    private <T extends Interceptor> List<T> getInterceptors(final Object entity, final Class<T> interceptorType) {
        if (interceptorMapping == null) {
            interceptorMapping = applicationContext.getBean(INTERCEPTOR_MAPPING_BEAN_NAME, Map.class);
        }
        if (MapUtils.isNotEmpty(interceptorMapping)) {
            final List<Class<? extends AbstractEntity>> entityClassList = new ArrayList<>();
            entityClassList.add((Class<? extends AbstractEntity>) entity.getClass());
            entityClassList.addAll(ClassUtils.getAllSuperclasses(entity.getClass()).stream()
                    .filter(clazz -> !Object.class.equals(clazz) && !AbstractEntity.class.equals(clazz))
                    .map(clazz -> (Class<? extends AbstractEntity>) clazz)
                    .collect(Collectors.toList()));
            Collections.reverse(entityClassList);

            final Set<T> allInterceptors = new LinkedHashSet<>();
            entityClassList.forEach(targetClass -> {
                final List<T> interceptors = (List<T>) interceptorMapping.get(targetClass);
                if (CollectionUtils.isNotEmpty(interceptors)) {
                    allInterceptors.addAll(interceptors);
                }
            });
            if (CollectionUtils.isNotEmpty(allInterceptors)) {
                return allInterceptors.stream().filter(interceptorType::isInstance)
                        .map(interceptorType::cast).collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

    private InterceptorContext createInterceptorContext(final Serializable id, final Object[] state,
                                                        final String[] propertyNames, final Type[] types) {
        return new InterceptorContext(id, state, propertyNames, types);
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
