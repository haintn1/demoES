package digi.ecomm.elasticpathsdk.model.hierarchy.relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HierarchyNodeChildrenModel {
    @JsonProperty("data")
    private List<HierarchyNodeChildrenDataModel> data;

    @Override
    public String toString() {
        return "HierarchyNodeChildrenModel{" + "data=" + data + '}';
    }
}
