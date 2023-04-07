package digi.ecomm.commercesearch.index.data;

import digi.ecomm.entity.EnumValue;

public enum Tokenizer implements EnumValue {
    STANDARD("standard");

    private String value;

    Tokenizer(final String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
