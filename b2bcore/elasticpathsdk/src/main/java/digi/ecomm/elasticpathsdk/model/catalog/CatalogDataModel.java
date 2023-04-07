package digi.ecomm.elasticpathsdk.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CatalogDataModel {
    @JsonProperty("attributes")
    private CatalogAttributesModel attributes;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;
}
