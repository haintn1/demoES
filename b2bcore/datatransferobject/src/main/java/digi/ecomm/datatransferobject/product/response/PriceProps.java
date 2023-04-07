package digi.ecomm.datatransferobject.product.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PriceProps {

    @JsonProperty("priceListId")
    private String priceListId;

    @JsonProperty("priceValue")
    private Double priceValue;

    @JsonProperty("groupId")
    private String groupId;

    @JsonProperty("minPrice")
    private Double minPrice;

    @JsonProperty("maxPrice")
    private Double maxPrice;
}
