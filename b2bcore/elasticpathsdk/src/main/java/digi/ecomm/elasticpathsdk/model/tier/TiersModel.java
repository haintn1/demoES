package digi.ecomm.elasticpathsdk.model.tier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class TiersModel {
    @JsonProperty("min_5")
    private MinFiveModel minFive;
}
