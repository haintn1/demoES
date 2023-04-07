package digi.ecomm.elasticpathsdk.model.variation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class VariationAttributesModel {

    @JsonProperty("name")
    private String name;
}
