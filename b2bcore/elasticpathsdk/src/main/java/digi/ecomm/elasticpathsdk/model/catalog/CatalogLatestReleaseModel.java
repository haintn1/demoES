package digi.ecomm.elasticpathsdk.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import digi.ecomm.elasticpathsdk.model.base.LinksModel;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CatalogLatestReleaseModel {
    @JsonProperty("data")
    private CatalogLatestReleaseDataModel data;

    @JsonProperty("links")
    private LinksModel links;
}
