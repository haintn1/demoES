package digi.ecomm.datatransferobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PageableResponse<T> extends ListResultResponse<T> {

    private static final long serialVersionUID = 1L;

    @JsonProperty("page")
    private Pageable page;
}
