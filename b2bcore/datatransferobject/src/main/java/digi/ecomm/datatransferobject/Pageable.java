package digi.ecomm.datatransferobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class Pageable implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("totalElements")
    private long totalElements;

    @JsonProperty("currentPage")
    private int currentPage;

    @JsonProperty("pageSize")
    private int pageSize;

    @JsonProperty("totalPages")
    private int totalPages;
}
