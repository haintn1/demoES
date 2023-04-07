package digi.ecomm.elasticpathsdk.request.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public class Locale {

    @JsonProperty("fr-FR")
    private Fr fr;

    @Data
    public static class Fr {

        private String name;

        private String description;
    }
}
