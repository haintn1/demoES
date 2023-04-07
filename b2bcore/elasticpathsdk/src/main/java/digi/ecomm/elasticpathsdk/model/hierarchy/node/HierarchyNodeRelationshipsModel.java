package digi.ecomm.elasticpathsdk.model.hierarchy.node;

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
public class HierarchyNodeRelationshipsModel {
    @JsonProperty("children")
    private LinksListModel children;

    @JsonProperty("parent")
    private HierarchyNodeRelationshipsParentDataModel parent;

    @JsonProperty("products")
    private LinksListModel products;

    @Override
    public String toString() {
        return "HierarchyNodeRelationshipsModel{" + "children=" + children + ", parent=" + parent + ", products=" + products + '}';
    }
}
