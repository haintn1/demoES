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
public class VariationProps {

    @JsonProperty("variationName")
    private String variationName;

    @JsonProperty("optionValue")
    private String optionValue;
}

