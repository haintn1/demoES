package digi.ecomm.datatransferobject.search.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class SearchFacet implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("values")
    private List<Value> values = new ArrayList<>(); //NOSONAR

    @JsonIgnore
    private int priority;

    @NoArgsConstructor
    @Getter
    @Setter
    public static class Value {
        @JsonProperty("label")
        private String label;

        @JsonProperty("text")
        private String text;

        @JsonProperty("docCount")
        private long docCount;

        public Value(final String label, final String text, final long docCount) {
            this.label = label;
            this.text = text;
            this.docCount = docCount;
        }
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class RangeValue extends Value {
        @JsonProperty("from")
        private Object from;

        @JsonProperty("to")
        private Object to;

        public RangeValue(final String label, final String text, final Object from, final Object to, final long docCount) {
            super(label, text, docCount);
            this.from = from;
            this.to = to;
        }
    }
}
