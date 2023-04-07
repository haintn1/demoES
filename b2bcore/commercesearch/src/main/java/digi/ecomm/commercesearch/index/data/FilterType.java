package digi.ecomm.commercesearch.index.data;

import digi.ecomm.entity.EnumValue;

public enum FilterType implements EnumValue {
    STOP("stop"), SYNONYM("synonym");

    private String value;

    FilterType(final String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
