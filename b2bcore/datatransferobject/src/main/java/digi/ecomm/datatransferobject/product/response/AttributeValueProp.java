package digi.ecomm.datatransferobject.product.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttributeValueProp {

    @JsonProperty("label")
    private String label;

    @JsonProperty("value")
    private String value;
}
