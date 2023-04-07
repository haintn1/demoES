package digi.ecomm.elasticpathsdk.model.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import digi.ecomm.elasticpathsdk.model.base.LinksModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StocksModel {
    @JsonProperty("data")
    private List<StockDataModel> data;

    @JsonProperty("links")
    private LinksModel links;

    @Override
    public String toString() {
        return "StocksModel{" + "data=" + data + ", links=" + links + '}';
    }
}
