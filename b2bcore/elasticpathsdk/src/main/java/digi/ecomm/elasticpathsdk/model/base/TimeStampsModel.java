package digi.ecomm.elasticpathsdk.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class TimeStampsModel {
    @JsonProperty("created_at")
    private Date createdAt;

    @Override
    public String toString() {
        return "TimeStampsModel{" + "createdAt=" + createdAt + '}';
    }
}
