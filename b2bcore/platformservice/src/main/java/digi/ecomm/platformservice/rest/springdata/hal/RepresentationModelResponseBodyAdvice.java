package digi.ecomm.platformservice.rest.springdata.hal;

import org.springframework.core.MethodParameter;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.TypeConstrainedMappingJackson2HttpMessageConverter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.stream.Collectors;

public class RepresentationModelResponseBodyAdvice<T extends RepresentationModel<T>>
        implements ResponseBodyAdvice<RepresentationModel<T>> {

    private static final String PROFILE_REL = "profile";

    @Override
    public boolean supports(final MethodParameter returnType, final Class<? extends HttpMessageConverter<?>> converterType) {
        return TypeConstrainedMappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public RepresentationModel<T> beforeBodyWrite(
            final RepresentationModel<T> body, final MethodParameter returnType, final MediaType selectedContentType,
            final Class<? extends HttpMessageConverter<?>> selectedConverterType, final ServerHttpRequest request,
            final ServerHttpResponse response) {

        if (body instanceof RepositoryLinksResource) {
            final List<Link> links = body.getLinks().stream()
                    .filter(link -> !link.getRel().isSameAs(LinkRelation.of(PROFILE_REL)))
                    .collect(Collectors.toList());
            body.removeLinks();
            body.add(links);
            return body;
        }

        if (body instanceof PagedModel) {
            final PagedModel originalPagedModel = (PagedModel) body; //NOSONAR
            final PagedModel.PageMetadata originalPageMetadata = originalPagedModel.getMetadata();

            if (originalPageMetadata != null) {
                final SimplePagedModel.PageMetadata pageMetadata = new SimplePagedModel.PageMetadata(originalPageMetadata.getSize(),
                        originalPageMetadata.getNumber(), originalPageMetadata.getTotalElements(), originalPageMetadata.getTotalPages());
                return SimplePagedModel.of(originalPagedModel.getContent(), pageMetadata);
            } else {
                return SimplePagedModel.of(originalPagedModel.getContent(), new SimplePagedModel.PageMetadata());
            }
        }

        if (body instanceof PersistentEntityResource) {
            body.removeLinks();
            return new PersistentEntityResourceWrapper((PersistentEntityResource) body); //NOSONAR
        }

        return body;
    }
}
