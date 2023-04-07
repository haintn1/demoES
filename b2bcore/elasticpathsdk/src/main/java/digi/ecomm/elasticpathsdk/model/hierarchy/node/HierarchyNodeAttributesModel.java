package digi.ecomm.elasticpathsdk.model.hierarchy.node;

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
public class HierarchyNodeAttributesModel {
    @JsonProperty("description")
    private String description;

    @JsonProperty("name")
    private String name;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("locales")
    private LocalesModel locales;

    @Override
    public String toString() {
        return "HierarchyNodeAttributesModel{" + "description='" + description + '\'' + ", name='" + name + '\'' + ", slug='" + slug
                + '\'' + ", locales=" + locales + '}';
    }
}
