package digi.ecomm.elasticpathsdk.model.hierarchy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HierarchyDataModel {
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("attributes")
    private HierarchyAttributesModel attributes;

    @JsonProperty("relationships")
    private HierarchyRelationshipsModel relationships;

    @Override
    public String toString() {
        return "HierarchyDataModel{" + "id='" + id + '\'' + ", type='" + type + '\'' + ", attributes=" + attributes
                + ", relationships=" + relationships + '}';
    }
}
