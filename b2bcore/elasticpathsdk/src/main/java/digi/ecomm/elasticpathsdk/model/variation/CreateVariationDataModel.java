package digi.ecomm.elasticpathsdk.model.variation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateVariationDataModel {

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private static final String TYPE = "product-variation";

    @JsonProperty("attributes")
    private VariationAttributesModel attributes;
}
