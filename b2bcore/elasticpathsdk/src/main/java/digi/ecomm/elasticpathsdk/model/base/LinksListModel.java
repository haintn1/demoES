package digi.ecomm.elasticpathsdk.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class LinksListModel {
    @JsonProperty("links")
    private LinksRelatedModel links;

    @JsonProperty("data")
    private List<String> data;
}
