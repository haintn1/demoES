package digi.ecomm.entity;

import javax.persistence.AttributeConverter;
import java.util.Objects;

public abstract class AbstractAttributeConverter<T extends EnumValue> implements AttributeConverter<T, String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public final String convertToDatabaseColumn(final T attribute) {
        return Objects.nonNull(attribute) ? attribute.getValue() : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final T convertToEntityAttribute(final String dbData) {
        return Objects.nonNull(dbData) ? EnumValue.of(dbData, getEnumValues()) : null;
    }

    /**
     * Get enum's values.
     *
     * @return array of enum values
     */
    protected abstract T[] getEnumValues();
}
