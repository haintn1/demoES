package digi.ecomm.entity.pcm.converter;

import digi.ecomm.entity.AbstractAttributeConverter;
import digi.ecomm.entity.pcm.AttributeType;

import javax.persistence.Converter;

@Converter
public class AttributeTypeConverter extends AbstractAttributeConverter<AttributeType> {

    @Override
    protected AttributeType[] getEnumValues() {
        return AttributeType.values();
    }
}
