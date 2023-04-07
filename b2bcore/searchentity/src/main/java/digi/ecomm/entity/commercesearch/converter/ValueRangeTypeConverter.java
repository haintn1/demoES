package digi.ecomm.entity.commercesearch.converter;

import digi.ecomm.entity.AbstractAttributeConverter;
import digi.ecomm.entity.commercesearch.EsValueRangeType;

import javax.persistence.Converter;

@Converter
public class ValueRangeTypeConverter extends AbstractAttributeConverter<EsValueRangeType> {

    @Override
    protected EsValueRangeType[] getEnumValues() {
        return EsValueRangeType.values();
    }
}
