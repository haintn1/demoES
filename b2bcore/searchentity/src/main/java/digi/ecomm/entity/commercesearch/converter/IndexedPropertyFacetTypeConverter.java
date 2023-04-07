package digi.ecomm.entity.commercesearch.converter;

import digi.ecomm.entity.AbstractAttributeConverter;
import digi.ecomm.entity.commercesearch.EsIndexedPropertyFacetType;

import javax.persistence.Converter;

@Converter
public class IndexedPropertyFacetTypeConverter extends AbstractAttributeConverter<EsIndexedPropertyFacetType> {

    @Override
    protected EsIndexedPropertyFacetType[] getEnumValues() {
        return EsIndexedPropertyFacetType.values();
    }
}
