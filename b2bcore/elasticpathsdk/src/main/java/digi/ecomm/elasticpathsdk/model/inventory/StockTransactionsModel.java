package digi.ecomm.elasticpathsdk.model.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockTransactionsModel {
    @JsonProperty("data")
    private List<StockTransactionDataModel> data;

    @Override
    public String toString() {
        return "StockTransactionsModel{" + "data=" + data + '}';
    }
}
