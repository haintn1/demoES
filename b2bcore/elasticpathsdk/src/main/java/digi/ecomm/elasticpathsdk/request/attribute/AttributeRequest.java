package digi.ecomm.elasticpathsdk.request.attribute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttributeRequest {

    private List<DataAttributeRequest> data;
}
