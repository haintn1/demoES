package digi.ecomm.elasticpathsdk.response.entries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import digi.ecomm.elasticpathsdk.request.product.Link;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntryData {

    private String id;

    private String type;

    private String skinType;

    private Link link;

}
