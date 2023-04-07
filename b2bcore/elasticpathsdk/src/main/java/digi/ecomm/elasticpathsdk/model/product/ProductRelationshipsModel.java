package digi.ecomm.elasticpathsdk.model.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ProductRelationshipsModel {
    private String mainImageId;

    @JsonProperty("main_image")
    private void unpackMainImageId(final Map<String, Map<String, String>> mainImage) {
        final Map<String, String> data = mainImage.get("data");
        if (data != null) {
            this.mainImageId = data.get("id");
        }
    }
}
