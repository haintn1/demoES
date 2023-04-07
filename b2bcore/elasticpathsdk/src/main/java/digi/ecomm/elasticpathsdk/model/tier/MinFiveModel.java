package digi.ecomm.elasticpathsdk.model.tier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import digi.ecomm.elasticpathsdk.model.price.PriceModel;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MinFiveModel {
    @JsonProperty("minimum_quantity")
    private String minimumQuantity;

    @JsonProperty("price")
    private PriceModel price;
}
