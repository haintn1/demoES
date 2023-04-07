package digi.ecomm.elasticpathsdk.model.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import digi.ecomm.elasticpathsdk.model.price.PriceModel;
import digi.ecomm.elasticpathsdk.model.tier.TiersModel;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ProductAttributesModel {
    @JsonProperty("base_product")
    private Boolean baseProduct;

    @JsonProperty("commodity_type")
    private String commodityType;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("description")
    private String description;

    @JsonProperty("manage_stock")
    private Boolean manageStock;

    @JsonProperty("manufacturer_part_num")
    private String manufacturerPartNum;

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

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("published_at")
    private String publishedAt;

    @JsonProperty("price")
    private PriceModel price;

    @JsonProperty("tiers")
    private TiersModel tiers;

    @JsonProperty("meta")
    private ProductAttributesMetaModel meta;
}
