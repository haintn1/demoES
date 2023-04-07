package digi.ecomm.elasticpathsdk.model.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import digi.ecomm.elasticpathsdk.model.base.TimeStampsModel;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockTransactionDataModel {
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("action")
    private String action;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("timestamps")
    private TimeStampsModel timestamps;

    @Override
    public String toString() {
        return "StockTransactionDataModel{" + "id='" + id + '\'' + ", type='" + type + '\'' + ", action='" + action + '\''
                + ", productId='" + productId + '\'' + ", quantity=" + quantity + ", timestamps=" + timestamps + '}';
    }
}
