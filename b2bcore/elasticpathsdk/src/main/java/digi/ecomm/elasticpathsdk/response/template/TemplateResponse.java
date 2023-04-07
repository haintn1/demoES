package digi.ecomm.elasticpathsdk.response.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import digi.ecomm.elasticpathsdk.request.template.DataTemplateDto;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateResponse {

    private DataTemplateDto data;
}
