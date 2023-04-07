package digi.ecomm.elasticpathsdk.model.hierarchy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import digi.ecomm.elasticpathsdk.model.localization.LocalesModel;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HierarchyAttributesModel {
    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("description")
    private String description;

    @JsonProperty("name")
    private String name;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("published_at")
    private String publishedAt;

    @JsonProperty("locales")
    private LocalesModel locales;

    @Override
    public String toString() {
        return "HierarchyAttributesModel{" + "createdAt='" + createdAt + '\'' + ", description='" + description + '\''
                + ", name='" + name + '\'' + ", slug='" + slug + '\'' + ", updatedAt='" + updatedAt + '\'' + ", publishedAt='"
                + publishedAt + '\'' + ", locales=" + locales + '}';
    }
}
