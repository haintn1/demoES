package digi.ecomm.elasticpathsdk.request.attribute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataAttributeRequest {

    private String type;

    private String name;

    private String slug;

    @JsonProperty("field_type")
    private String fieldType;

    private String description;

    private boolean required;

    @JsonProperty("omit_null")
    private boolean omitNull;

    @JsonProperty("default")
    private int defaultValue;

    private boolean enabled;

    private int order;

    private RelationshipAttribute relationships;

}
