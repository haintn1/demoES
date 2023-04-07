package digi.ecomm.elasticpathsdk.model.hierarchy.node;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HierarchyNodeDataModel {
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("attributes")
    private HierarchyNodeAttributesModel attributes;

    @JsonProperty("relationships")
    private HierarchyNodeRelationshipsModel relationships;

    @Override
    public String toString() {
        return "HierarchyNodeDataModel{" + "id='" + id + '\'' + ", type='" + type + '\'' + ", attributes=" + attributes
                + ", relationships=" + relationships + '}';
    }
}
