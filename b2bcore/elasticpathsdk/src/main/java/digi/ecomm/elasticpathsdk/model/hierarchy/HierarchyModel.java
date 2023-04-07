package digi.ecomm.elasticpathsdk.model.hierarchy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import digi.ecomm.elasticpathsdk.model.base.LinksModel;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HierarchyModel {
    @JsonProperty("data")
    private HierarchyDataModel data;

    @JsonProperty("links")
    private LinksModel links;

    @Override
    public String toString() {
        return "HierarchyModel{" + "data=" + data + ", links=" + links + '}';
    }
}
