package digi.ecomm.datatransferobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListResultResponse<T> extends AbstractResponse {

    private static final long serialVersionUID = 1L;

    @JsonProperty("data")
    private List<T> results; //NOSONAR
}
