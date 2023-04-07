package digi.ecomm.elasticpathsdk.model.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PriceModel {
    @JsonProperty("USD")
    private CurrencyModel usd;

    @JsonProperty("CAD")
    private CurrencyModel cad;

    @JsonProperty("GBP")
    private CurrencyModel gbp;
}
