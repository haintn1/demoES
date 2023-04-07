package digi.ecomm.datatransferobject.search.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import digi.ecomm.datatransferobject.PageableResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class ProductSearchResponse extends PageableResponse<Map> {

    private static final long serialVersionUID = 1L;

    @JsonProperty("facets")
    private List<SearchFacet> facets;

    @JsonProperty("sorts")
    private List<SearchSort> sorts;
}
