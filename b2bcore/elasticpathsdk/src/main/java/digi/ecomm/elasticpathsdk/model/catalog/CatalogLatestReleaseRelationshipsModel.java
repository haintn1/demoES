package digi.ecomm.elasticpathsdk.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import digi.ecomm.elasticpathsdk.model.base.LinksListModel;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CatalogLatestReleaseRelationshipsModel {
    @JsonProperty("hierarchies")
    private CatalogLatestReleaseRelationshipsHierarchiesModel hierarchies;

    @JsonProperty("products")
    private LinksListModel products;
}
