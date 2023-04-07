package digi.ecomm.elasticpathsdk.model.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDataModel {
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("total")
    private int total;

    @JsonProperty("available")
    private int available;

    @JsonProperty("allocated")
    private int allocated;

    @Override
    public String toString() {
        return "StockModel{" + "id='" + id + '\'' + ", type='" + type + '\'' + ", total=" + total + ", available=" + available
                + ", allocated=" + allocated + '}';
    }
}
