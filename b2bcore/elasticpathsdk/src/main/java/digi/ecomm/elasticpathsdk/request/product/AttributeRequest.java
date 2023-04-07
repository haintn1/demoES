package digi.ecomm.elasticpathsdk.request.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttributeRequest {

    private String name;

    private String sku;

    private String slug;

    private String description;

    private String status;

    @JsonProperty("commodity_type")
    private String commodityType;

    @JsonProperty("upc_ean")
    private String upcEan;

    private String mpn;

    private Locale locales;
}
