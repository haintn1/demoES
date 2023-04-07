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
public class HierarchyNodeRelationshipsParentDataModel {
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @Override
    public String toString() {
        return "HierarchyNodeRelationshipsParentDataModel{" + "id='" + id + '\'' + ", type='" + type + '\'' + '}';
    }
}
