package digi.ecomm.elasticpathsdk.request.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import digi.ecomm.elasticpathsdk.request.product.Link;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataTemplateDto {

    private String type;

    private String name;

    private String slug;

    private String description;

    private boolean enabled;

    private String id;

    private Link links;


}
