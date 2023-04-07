package digi.ecomm.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("timestamp")
    @Setter(AccessLevel.NONE)
    private Date timestamp = new Date();

    @JsonProperty("errors")
    private List<String> errors;
}
