package digi.ecomm.datatransferobject.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public abstract class AbstractIndexedSourceItem {

    @JsonProperty("id")
    private Long id;

    private Map<String, Object> dynamicFields;
}
