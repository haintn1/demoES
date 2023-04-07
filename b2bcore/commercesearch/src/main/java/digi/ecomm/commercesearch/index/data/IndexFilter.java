package digi.ecomm.commercesearch.index.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class IndexFilter implements ToMap {
    protected static final String TYPE_KEY = "type";
    protected static final String UNDERSCORE = "_";

    private String name;
    private FilterType type;
}
