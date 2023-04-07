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
public class ProductAttributesMetaModel {
    @JsonProperty("catalog_id")
    private String catalogId;

    @JsonProperty("catalog_source")
    private String catalogSource;

    @JsonProperty("original_price")
    private PriceModel originalPrice;

    @JsonProperty("tiers")
    private TiersModel tiers;

    @JsonProperty("sale_expires")
    private String saleExpires;

    @JsonProperty("saleId")
    private String saleId;
}
