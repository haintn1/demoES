package digi.ecomm.commercesearch.strategy.impl;

import digi.ecomm.commercesearch.data.IndexedPropertyGroup;
import digi.ecomm.commercesearch.strategy.EsIndexedPropertyGroupStrategy;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.entity.commercesearch.EsPropertyType;
import digi.ecomm.platformservice.util.ServicesUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EsIndexedPropertyGroupStrategyImpl implements EsIndexedPropertyGroupStrategy {
    @Override
    public List<IndexedPropertyGroup> group(final List<EsIndexedProperty> indexedProperties) {
        ServicesUtils.validateParameterNotNullStandardMessage("indexedProperties", indexedProperties);

        final List<IndexedPropertyGroup> propertyGroups = new ArrayList<>();
        indexedProperties.forEach(property -> {
            if (Objects.isNull(property.getOuterProperty())) {
                final IndexedPropertyGroup propertyGroup = new IndexedPropertyGroup();
                propertyGroup.setProperty(property);
                propertyGroup.setNestedProperties(indexedProperties.stream()
                        .filter(nestedProperty -> !Objects.equals(nestedProperty, property))
                        .filter(nestedProperty -> Objects.nonNull(nestedProperty.getOuterProperty()))
                        .filter(nestedProperty -> Objects.equals(nestedProperty.getOuterProperty().getType(), EsPropertyType.NESTED))
                        .filter(nestedProperty -> Objects.equals(nestedProperty.getOuterProperty(), property))
                        .collect(Collectors.toList()));
                propertyGroups.add(propertyGroup);
            }
        });
        return propertyGroups;
    }
}
