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
public class HierarchyNodeChildrenMetaModel {
    @JsonProperty("sort_order")
    private int sortOrder;
}
