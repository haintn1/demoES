package digi.ecomm.platformservice.persistent.specification;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QueryCriteria {
    private String field;
    private QueryOperation operator;
    private Object value;
    private List<Object> values; //Used in case of IN operator

    public QueryCriteria(final String field, final QueryOperation operator, final Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public QueryCriteria(final String field, final QueryOperation operator, final List<Object> values) {
        this.field = field;
        this.operator = operator;
        this.values = values;
    }
}
