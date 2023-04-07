package digi.ecomm.elasticpathsdk.model.hierarchy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import digi.ecomm.elasticpathsdk.model.base.LinksListModel;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HierarchyRelationshipsModel {
    @JsonProperty("children")
    private LinksListModel children;

    @JsonProperty("nodes")
    private LinksListModel nodes;

    @JsonProperty("products")
    private LinksListModel products;

    @Override
    public String toString() {
        return "HierarchyRelationshipsModel{" + "children=" + children + ", nodes=" + nodes + ", products=" + products + '}';
    }
}
