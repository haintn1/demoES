package digi.ecomm.datatransferobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SingleResultResponse<T extends Serializable> extends AbstractResponse {

    private static final long serialVersionUID = 1L;

    @JsonProperty("data")
    private T result;
}
