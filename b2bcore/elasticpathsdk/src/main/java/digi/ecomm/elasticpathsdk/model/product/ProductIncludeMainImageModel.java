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
public class ProductIncludeMainImageModel {
    @JsonProperty("id")
    private String id;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("mime_type")
    private String mimeType;

    @JsonProperty("file_size")
    private long fileSizeInByte;

    private String href;

    @JsonProperty("link")
    private void unpackHref(final Map<String, String> link) {
        this.href = link.get("href");
    }
}
