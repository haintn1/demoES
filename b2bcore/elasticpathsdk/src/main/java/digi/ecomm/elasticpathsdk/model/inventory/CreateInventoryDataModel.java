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
public class CreateInventoryDataModel {
    @JsonProperty("quantity")
    private int quantity;

    @Override
    public String toString() {
        return "CreateInventoryDataModel{" + "quantity=" + quantity + '}';
    }
}
