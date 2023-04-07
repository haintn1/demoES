package digi.ecomm.elasticpathsdk.model.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CurrencyModel {
    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("includes_tax")
    private Boolean includesTax;
}
