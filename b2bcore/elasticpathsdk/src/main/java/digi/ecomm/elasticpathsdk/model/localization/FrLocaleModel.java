package digi.ecomm.elasticpathsdk.model.localization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class FrLocaleModel {
    @JsonProperty("description")
    private String description;

    @JsonProperty("name")
    private String name;

    @Override
    public String toString() {
        return "FrLocaleModel{" + "description='" + description + '\'' + ", name='" + name + '\'' + '}';
    }
}
