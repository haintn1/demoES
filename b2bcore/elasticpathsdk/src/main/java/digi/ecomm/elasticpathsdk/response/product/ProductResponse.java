package digi.ecomm.elasticpathsdk.response.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import digi.ecomm.elasticpathsdk.request.product.DataDto;
import digi.ecomm.elasticpathsdk.request.product.Relationship;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {

    private DataDto data;

    private Relationship relationships;
}
