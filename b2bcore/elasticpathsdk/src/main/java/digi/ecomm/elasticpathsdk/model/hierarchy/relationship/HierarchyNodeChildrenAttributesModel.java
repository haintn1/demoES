package digi.ecomm.elasticpathsdk.model.hierarchy.relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HierarchyNodeChildrenAttributesModel {
    @JsonProperty("description")
    private String description;

    @JsonProperty("name")
    private String name;

    @JsonProperty("slug")
    private String slug;

    @Override
    public String toString() {
        return "HierarchyNodeChildrenAttributesModel{" + "description='" + description + '\'' + ", name='" + name + '\''
                + ", slug='" + slug + '\'' + '}';
    }
}
