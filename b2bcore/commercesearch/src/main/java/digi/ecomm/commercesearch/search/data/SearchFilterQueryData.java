package digi.ecomm.commercesearch.search.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class SearchFilterQueryData {
    private String key;
    private FilterQueryOperator operator;
    private Set<String> values;
}
