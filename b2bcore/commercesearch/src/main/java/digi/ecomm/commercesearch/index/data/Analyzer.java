package digi.ecomm.commercesearch.index.data;

import digi.ecomm.entity.EnumValue;

public enum Analyzer implements EnumValue {
    DEFAULT("default"), DEFAULT_SEARCH("default_search");

    private String value;

    Analyzer(final String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
