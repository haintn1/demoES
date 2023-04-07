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
public class HierarchyNodeProductAttributesModel {
    @JsonProperty("commodity_type")
    private String commodityType;

    @JsonProperty("description")
    private String description;

    @JsonProperty("mpn")
    private String mpn;

    @JsonProperty("name")
    private String name;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("status")
    private String status;

    @JsonProperty("upc_ean")
    private String upcEan;

    @Override
    public String toString() {
        return "HierarchyNodeProductAttributesModel{" + "commodityType='" + commodityType + '\'' + ", description='" + description
                + '\'' + ", mpn='" + mpn + '\'' + ", name='" + name + '\'' + ", sku='" + sku + '\'' + ", slug='" + slug + '\''
                + ", status='" + status + '\'' + ", upcEan='" + upcEan + '\'' + '}';
    }
}
