package digi.ecomm.entity.commercesearch;

import digi.ecomm.entity.EnumValue;

public enum EsValueRangeType implements EnumValue {
    DATE("date"), INTEGER("integer"), LONG("long"), FLOAT("float"), DOUBLE("double");

    private String value;

    EsValueRangeType(final String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
