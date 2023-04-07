package digi.ecomm.elasticpathsdk.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CatalogLatestAttributesRelease {
    @JsonProperty("hierarchies")
    private List<CatalogLatestReleaseHierarchiesModel> hierarchies;

    @JsonProperty("description")
    private String description;

    @JsonProperty("name")
    private String name;

    @JsonProperty("published_at")
    private String publishedAt;
}
