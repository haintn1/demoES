package digi.ecomm.entity.store;

import digi.ecomm.entity.EnumValue;

public enum DistanceUnit implements EnumValue {
    MILES("miles"), KM("km");

    private String value;

    DistanceUnit(final String value) {
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
