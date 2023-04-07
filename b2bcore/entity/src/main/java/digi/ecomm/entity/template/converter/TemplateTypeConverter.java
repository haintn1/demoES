package digi.ecomm.entity.template.converter;

import digi.ecomm.entity.AbstractAttributeConverter;
import digi.ecomm.entity.template.TemplateType;

import javax.persistence.Converter;

@Converter
public class TemplateTypeConverter extends AbstractAttributeConverter<TemplateType> {

    @Override
    protected TemplateType[] getEnumValues() {
        return TemplateType.values();
    }
}
