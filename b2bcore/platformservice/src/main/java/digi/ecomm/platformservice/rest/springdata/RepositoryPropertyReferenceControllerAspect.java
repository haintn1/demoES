package digi.ecomm.platformservice.rest.springdata;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.mapping.PersistentProperty;
import org.springframework.data.repository.support.RepositoryInvoker;
import org.springframework.data.rest.core.mapping.PropertyAwareResourceMapping;
import org.springframework.data.rest.core.mapping.ResourceMetadata;
import org.springframework.data.rest.webmvc.ControllerUtils;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.rest.webmvc.RootResourceInformation;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.core.EmbeddedWrappers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Aspect
@Component
@RequiredArgsConstructor
public class RepositoryPropertyReferenceControllerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryPropertyReferenceControllerAspect.class);

    private static final EmbeddedWrappers WRAPPERS = new EmbeddedWrappers(false);
    private final PagedResourcesAssembler<Object> pagedResourcesAssembler;

    /**
     * Override property reference logic to resolve resource not found due to JPA collection lazy loading.
     *
     * @param proceedingJoinPoint
     * @return {@link RepresentationModel}
     * @throws Throwable
     */
    @Around(value = "execution(* org.springframework.data.rest.webmvc.RepositoryPropertyReferenceController.followPropertyReference(..))")
    public Object followPropertyReference(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        final String className = methodSignature.getDeclaringType().getName();
        final String methodName = methodSignature.getName();
        final String[] parameterNames = methodSignature.getParameterNames();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Executing {}.{}({})", className, methodName, StringUtils.join(parameterNames, ","));
        }

        final Object[] args = proceedingJoinPoint.getArgs();
        final RootResourceInformation resourceInformation = (RootResourceInformation) args[0];
        final Serializable id = (Serializable) args[1];
        final String propertyPath = (String) args[2];
        final PersistentEntityResourceAssembler assembler = (PersistentEntityResourceAssembler) args[3];

        final ResourceMetadata metadata = resourceInformation.getResourceMetadata();
        final PropertyAwareResourceMapping mapping = metadata.getProperty(propertyPath);

        if (mapping == null || !mapping.isExported()) {
            throw new ResourceNotFoundException();
        }

        final PersistentProperty<?> property = mapping.getProperty();
        resourceInformation.verifySupportedMethod(HttpMethod.GET, property);
        final RepositoryInvoker invoker = resourceInformation.getInvoker();
        final Object domainObj = invoker.invokeFindById(id).orElseThrow(ResourceNotFoundException::new);

        final ReferencedProperty prop;
        final Method getter = property.getGetter();
        if (getter == null) {
            LOGGER.warn("No getter for property {} in type {}", property.getName(), metadata.getDomainType().getName());
            prop = new ReferencedProperty(property, null);
        } else {
            prop = new ReferencedProperty(property, getter.invoke(domainObj));
        }

        final HttpHeaders headers = new HttpHeaders();

        final Optional<CollectionModel<?>> collectionModel = prop.mapValue(it -> {
            if (prop.property.isCollectionLike()) {
                return toCollectionModel((Iterable<?>) it, assembler, prop.propertyType, Optional.empty());
            }
            return null;
        });

        if (!collectionModel.isPresent()) {
            return proceedingJoinPoint.proceed();
        }

        return ControllerUtils.toResponseEntity(HttpStatus.OK, headers, collectionModel);
    }

    private CollectionModel<?> toCollectionModel(final Iterable<?> source, final PersistentEntityResourceAssembler assembler,
                                                 final Class<?> domainType, final Optional<Link> baseLink) {

        if (source == null) {
            return CollectionModel.empty();
        }
        if (source instanceof Page) {
            return entitiesToResources((Page<Object>) source, assembler, domainType, baseLink);
        }
        return entitiesToResources((Iterable<Object>) source, assembler, domainType);
    }

    private CollectionModel<?> entitiesToResources(final Page<Object> page, final PersistentEntityResourceAssembler assembler,
                                                   final Class<?> domainType, final Optional<Link> baseLink) {

        if (page.getContent().isEmpty()) {
            return baseLink.<PagedModel<?>>map(it -> pagedResourcesAssembler.toEmptyModel(page, domainType, it))
                    .orElseGet(() -> pagedResourcesAssembler.toEmptyModel(page, domainType));
        }

        return baseLink.map(it -> pagedResourcesAssembler.toModel(page, assembler, it))
                .orElseGet(() -> pagedResourcesAssembler.toModel(page, assembler));
    }

    private CollectionModel<?> entitiesToResources(final Iterable<Object> entities,
                                                   final PersistentEntityResourceAssembler assembler, final Class<?> domainType) {

        if (!entities.iterator().hasNext()) {
            List<Object> content = Arrays.<Object>asList(WRAPPERS.emptyCollectionOf(domainType));
            return CollectionModel.of(content, getDefaultSelfLink());
        }

        final List<EntityModel<Object>> resources = new ArrayList<>();

        for (Object obj : entities) {
            resources.add(obj == null ? null : assembler.toModel(obj));
        }

        return CollectionModel.of(resources, getDefaultSelfLink());
    }

    private Link getDefaultSelfLink() {
        return Link.of(ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString());
    }

    private final class ReferencedProperty {

        private final PersistentProperty<?> property;
        private final Class<?> propertyType;
        private final Object propertyValue;

        private ReferencedProperty(final PersistentProperty<?> property, final Object propertyValue) {
            this.property = property;
            this.propertyValue = propertyValue;
            this.propertyType = property.getActualType();
        }

        public <T> Optional<T> mapValue(final Function<Object, T> function) {
            return Optional.ofNullable(propertyValue).map(function);
        }
    }
}
