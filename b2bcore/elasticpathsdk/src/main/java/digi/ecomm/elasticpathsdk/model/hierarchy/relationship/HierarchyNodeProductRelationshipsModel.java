package digi.ecomm.elasticpathsdk.model.hierarchy.relationship;

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
public class HierarchyNodeProductRelationshipsModel {
    @JsonProperty("files")
    private LinksListModel files;

    @JsonProperty("templates")
    private LinksListModel templates;

    @Override
    public String toString() {
        return "HierarchyNodeProductRelationshipsModel{" + "files=" + files + ", templates=" + templates + '}';
    }
}
