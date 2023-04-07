package digi.ecomm.entity.commercesearch;

import digi.ecomm.entity.EnumValue;

public enum EsIndexedPropertyFacetType implements EnumValue {
    REFINE("Refine"), MULTISELECT_AND("MultiSelectAnd"), MULTISELECT_OR("MultiSelectOr");

    private String value;

    EsIndexedPropertyFacetType(final String value) {
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
