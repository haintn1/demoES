package digi.ecomm.commercesearch.search.data;

public enum IndexFilterType {
    STOP_WORDS("stop", "STOP_WORDS", "stopwords"),
    SYNONYM("synonym", "SYNONYMS", "synonyms");

    private String type;
    private String schema;
    private String symbol;

    IndexFilterType(final String type, final String schema, final String symbol) {
        this.type = type;
        this.schema = schema;
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public String getSchema() {
        return schema;
    }

    public String getSymbol() {
        return symbol;
    }
}