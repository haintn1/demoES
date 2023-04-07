package digi.ecomm.elasticpathsdk.response.attribute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import digi.ecomm.elasticpathsdk.request.attribute.AttributeRequest;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttributeResponse {

    private List<AttributeRequest> data;

    private MetaAttribute meta;
}
