package digi.ecomm.entity.pcm;


import digi.ecomm.entity.EnumValue;

public enum AttributeType implements EnumValue {
    STRING("string"), INTEGER("integer"), FLOAT("float"), DOUBLE("double"), DATE("date");

    private String value;

    AttributeType(final String value) {
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