package digi.ecomm.elasticpathsdk.model.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ProductDataModel {
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("attributes")
    private ProductAttributesModel attributes;

    @JsonProperty("relationships")
    private ProductRelationshipsModel relationships;

    @JsonProperty("meta")
    private ProductMetaModel meta;
}