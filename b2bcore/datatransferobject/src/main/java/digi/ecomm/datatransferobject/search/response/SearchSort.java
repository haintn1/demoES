package digi.ecomm.datatransferobject.search.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class SearchSort implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;
}
