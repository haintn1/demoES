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
public class HierarchyNodeModel {
    @JsonProperty("data")
    private HierarchyNodeDataModel data;

    @Override
    public String toString() {
        return "HierarchyNodeModel{" + "data=" + data + '}';
    }
}
