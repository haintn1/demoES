package digi.ecomm.elasticpathsdk.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ErrorModel {

    @JsonProperty("errors")
    private List<Error> errors;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    @Setter
    private static final class Error {

        @JsonProperty("status")
        private String status;

        @JsonProperty("title")
        private String title;

        @JsonProperty("detail")
        private String detail;

        @Override
        public String toString() {
            return new StringBuilder("status='").append(status)
                    .append("' title='").append(title).append("' detail='").append(detail).append("'").toString();
        }
    }

    @Override
    public String toString() {
        return errors != null ? errors.toString() : "[]";
    }
}
