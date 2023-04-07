package digi.ecomm.entity.commercesearch.converter;

import digi.ecomm.entity.AbstractAttributeConverter;
import digi.ecomm.entity.commercesearch.EsPropertyType;

import javax.persistence.Converter;

@Converter
public class IndexPropertyTypeConverter extends AbstractAttributeConverter<EsPropertyType> {

    @Override
    protected EsPropertyType[] getEnumValues() {
        return EsPropertyType.values();
    }
}
