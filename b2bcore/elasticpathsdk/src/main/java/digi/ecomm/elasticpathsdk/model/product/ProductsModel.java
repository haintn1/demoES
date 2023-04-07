package digi.ecomm.elasticpathsdk.model.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import digi.ecomm.elasticpathsdk.model.base.LinksModel;
import digi.ecomm.elasticpathsdk.model.base.MetaModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ProductsModel {
    @JsonProperty("data")
    private List<ProductDataModel> data;

    @JsonProperty("links")
    private LinksModel links;

    @JsonProperty("included")
    private ProductIncludeModel include;

    @JsonProperty("meta")
    private MetaModel meta;
}
