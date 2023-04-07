package digi.ecomm.elasticpathsdk.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class LinksModel {
    @JsonProperty("first")
    private String first;

    @JsonProperty("last")
    private String last;

    @JsonProperty("self")
    private String self;

    @JsonProperty("current")
    private String current;

    @Override
    public String toString() {
        return "LinksModel{" + "first='" + first + '\'' + ", last='" + last + '\'' + ", self='" + self + '\'' + ", current='"
                + current + '\'' + '}';
    }
}
