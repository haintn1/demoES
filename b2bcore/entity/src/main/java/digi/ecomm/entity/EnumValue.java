package digi.ecomm.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

public interface EnumValue {

    /**
     * Get enum actual value.
     *
     * @return
     */
    String getValue();

    /**
     * Get enum instance from its value.
     *
     * @param value
     * @param values
     * @param <T>
     * @return
     */
    static <T extends EnumValue> T of(final String value, T[] values) {
        return Stream.of(values)
                .filter(t -> StringUtils.equals(t.getValue(), value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No match constant for value %s", value)));
    }
}
