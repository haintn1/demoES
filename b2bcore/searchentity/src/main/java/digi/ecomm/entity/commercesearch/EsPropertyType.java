package digi.ecomm.entity.commercesearch;

import digi.ecomm.entity.EnumValue;

public enum EsPropertyType implements EnumValue {
    TEXT("text"), KEYWORD("keyword"), BOOLEAN("boolean"), DATE("date"), INTEGER("integer"),
    LONG("long"), FLOAT("float"), DOUBLE("double"), NESTED("nested"), COMPLETION("completion");

    private String value;

    EsPropertyType(final String value) {
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
