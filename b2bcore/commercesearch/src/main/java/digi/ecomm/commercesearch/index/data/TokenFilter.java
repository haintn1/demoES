package digi.ecomm.commercesearch.index.data;

import digi.ecomm.entity.EnumValue;

public enum TokenFilter implements EnumValue {
    LOWERCASE("lowercase");

    private String value;

    TokenFilter(final String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
