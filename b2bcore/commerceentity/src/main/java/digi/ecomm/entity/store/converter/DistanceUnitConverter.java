package digi.ecomm.entity.store.converter;

import digi.ecomm.entity.AbstractAttributeConverter;
import digi.ecomm.entity.store.DistanceUnit;

import javax.persistence.Converter;

@Converter
public class DistanceUnitConverter extends AbstractAttributeConverter<DistanceUnit> {

    @Override
    protected DistanceUnit[] getEnumValues() {
        return DistanceUnit.values();
    }
}
