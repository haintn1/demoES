package digi.ecomm.elasticpathsdk.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CatalogAttributesModel {
    @JsonProperty("hierarchies")
    private List<CatalogLatestReleaseHierarchiesModel> hierarchies;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("description")
    private String description;

    @JsonProperty("hierarchy_ids")
    private List<String> hierarchyIds;

    @JsonProperty("name")
    private String name;

    @JsonProperty("pricebook_id")
    private String priceBookId;

    @JsonProperty("updated_at")
    private String updatedAt;
}
