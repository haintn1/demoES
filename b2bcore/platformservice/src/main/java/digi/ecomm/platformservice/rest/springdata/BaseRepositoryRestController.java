package digi.ecomm.platformservice.rest.springdata;

import digi.ecomm.platformservice.persistent.service.EntityService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestProperties;
import org.springframework.hateoas.Link;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public abstract class BaseRepositoryRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseRepositoryRestController.class);
    private static final String SLASH = "/";

    @Resource
    private RepositoryRestProperties repositoryRestProperties;

    @Resource
    protected EntityService entityService;

    /**
     * Build current resource self link.
     *
     * @param request
     * @return
     */
    protected Link buildCurrentResourceSelfRefLink(final HttpServletRequest request) {
        try {
            final URL url = new URL(UrlUtils.buildFullRequestUrl(request));
            return Link.of(UrlUtils.buildFullRequestUrl(url.getProtocol(), url.getHost(), url.getPort(), url.getPath(), null));
        } catch (final MalformedURLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Link.of(StringUtils.EMPTY);
    }

    public class LinkBuilder {
        private HttpServletRequest request;
        private String resourcePath;
        private String itemResourceRel;
        private String resourceIdentifier;
        private List<String> relationships;

        /**
         * Set request.
         *
         * @param request
         * @return
         */
        public LinkBuilder request(final HttpServletRequest request) {
            this.request = request;
            return this;
        }

        /**
         * Set resource path.
         *
         * @param resourcePath
         * @return
         */
        public LinkBuilder resourcePath(final String resourcePath) {
            this.resourcePath = resourcePath;
            return this;
        }

        /**
         * Set resource rel for a resource.
         *
         * @param itemResourceRel
         * @return
         */
        public LinkBuilder itemResourceRel(final String itemResourceRel) {
            this.itemResourceRel = itemResourceRel;
            return this;
        }

        /**
         * Set unique identifier of s resource.
         *
         * @param resourceIdentifier
         * @return
         */
        public LinkBuilder resourceIdentifier(final String resourceIdentifier) {
            this.resourceIdentifier = resourceIdentifier;
            return this;
        }

        /**
         * Set list of relationships.
         *
         * @param relationships
         * @return
         */
        public LinkBuilder relationships(final List<String> relationships) {
            if (relationships != null) {
                if (this.relationships == null) {
                    this.relationships = new ArrayList<>();
                }
                this.relationships.addAll(relationships);
            }
            return this;
        }

        /**
         * Set list of relationships.
         *
         * @param relationships
         * @return
         */
        public LinkBuilder relationships(final String... relationships) {
            if (relationships != null) {
                if (this.relationships == null) {
                    this.relationships = new ArrayList<>();
                }
                this.relationships.addAll(Stream.of(relationships).collect(Collectors.toList()));
            }
            return this;
        }

        /**
         * Set a relationship.
         *
         * @param relationship
         * @return
         */
        public LinkBuilder relationship(final String relationship) {
            if (this.relationships == null) {
                this.relationships = new ArrayList<>();
            }
            this.relationships.add(relationship);
            return this;
        }

        /**
         * Build a list of resource's links.
         *
         * @return
         */
        public List<Link> build() {
            final List<Link> links = new ArrayList<>();
            links.add(buildItemResourceLink(request, new Object[]{resourcePath, resourceIdentifier}));
            links.add(buildItemResourceLink(request, new Object[]{resourcePath, resourceIdentifier}, itemResourceRel));
            if (CollectionUtils.isNotEmpty(relationships)) {
                relationships.forEach(relationship -> links.add(buildItemResourceLink(request,
                        new Object[]{resourcePath, resourceIdentifier, relationship}, relationship)));
            }
            return links;
        }

        private Link buildItemResourceLink(final HttpServletRequest request, final Object[] pathSegments, final String rel) {
            return Link.of(buildItemResourceHref(request, StringUtils.join(pathSegments, SLASH)), rel);
        }

        private Link buildItemResourceLink(final HttpServletRequest request, final Object[] pathSegments) {
            return Link.of(buildItemResourceHref(request, StringUtils.join(pathSegments, SLASH)));
        }

        private String buildItemResourceHref(final HttpServletRequest request, final String resourcePath) {
            return StringUtils.join(buildBasePathUrl(request), SLASH, resourcePath);
        }

        private String buildBasePathUrl(final HttpServletRequest request) {
            try {
                final String contextPath = StringUtils.removeEnd(request.getContextPath(), SLASH);
                final String apiBasePath =
                        StringUtils.removeEnd(BaseRepositoryRestController.this.repositoryRestProperties.getBasePath(), SLASH);
                final URL url = new URL(UrlUtils.buildFullRequestUrl(request));
                return UrlUtils.buildFullRequestUrl(url.getProtocol(), url.getHost(), url.getPort(),
                        StringUtils.join(contextPath, apiBasePath), null);
            } catch (final MalformedURLException e) {
                LOGGER.error(e.getMessage(), e);
            }
            return StringUtils.EMPTY;
        }
    }
}
