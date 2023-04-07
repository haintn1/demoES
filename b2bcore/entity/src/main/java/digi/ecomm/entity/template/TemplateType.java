package digi.ecomm.entity.template;

import digi.ecomm.entity.EnumValue;

public enum TemplateType implements EnumValue {
    VELOCITY("velocity");

    private String value;

    TemplateType(final String value) {
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
