package digi.ecomm.searchcustomapi.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi facetSearchApis() {
        return GroupedOpenApi.builder().group("Facet search API")
                .pathsToMatch("/advanced-search-configs", "/advanced-search-configs/*",
                        "/boost-rules/", "/boost-rules/*",
                        "/facet-search-configs", "/facet-search-configs/*",
                        "/index-configs", "/index-configs/*",
                        "/indexed-properties", "/indexed-properties/*",
                        "/indices", "/indices/*",
                        "/promoted-items", "/promoted-items/*",
                        "/search-configs", "/search-configs/*",
                        "/server-configs", "/server-configs/*",
                        "/servers", "/servers/*",
                        "/sort-fields", "/sort-fields/*",
                        "/sorts", "/sorts/*",
                        "/stop-words", "/stop-words/*",
                        "/synonyms", "/synonyms/*",
                        "/value-ranges", "/value-ranges/*",
                        "/value-range-sets", "/value-range-sets/*",
                        "/products", "/products/*", "/cronjobs", "/cronjobs/*", "/cronjobs/**/start", "/cronjobs/**/stop",
                        "/cronjobs/**/resume", "/cronjobs/**/pause")
                .build();
    }
}
