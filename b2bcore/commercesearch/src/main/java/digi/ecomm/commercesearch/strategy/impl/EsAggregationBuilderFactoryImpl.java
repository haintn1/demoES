package digi.ecomm.commercesearch.strategy.impl;

import digi.ecomm.commercesearch.provider.FieldNameResolver;
import digi.ecomm.commercesearch.repository.EsValueRangeRepository;
import digi.ecomm.commercesearch.strategy.EsAggregationBuilderFactory;
import digi.ecomm.entity.commercesearch.*;
import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class EsAggregationBuilderFactoryImpl implements EsAggregationBuilderFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsAggregationBuilderFactoryImpl.class);

    private static final String DOT = ".";

    private final EsValueRangeRepository valueRangeRepository;
    private final FieldNameResolver fieldNameResolver;

    @Override
    public AggregationBuilder get(final EsIndexedProperty property) {
        ServicesUtils.validateParameterNotNullStandardMessage("property", property);

        final String fieldName = fieldNameResolver.resolve(property);

        if (Objects.equals(EsPropertyType.KEYWORD, property.getType())) {
            return createTermAggregationBuilder(property, fieldName);

        } else if (Objects.equals(EsPropertyType.KEYWORD, property.getMultiFieldsType())) {
            return createTermAggregationBuilder(property,
                    StringUtils.join(fieldName, DOT, property.getMultiFieldsType().getValue()));

        } else if (Objects.equals(EsPropertyType.DOUBLE, property.getType())
                || Objects.equals(EsPropertyType.FLOAT, property.getType())
                || Objects.equals(EsPropertyType.LONG, property.getType())
                || Objects.equals(EsPropertyType.INTEGER, property.getType())) {
            return createNumberRangeAggregationBuilder(property.getName(), fieldName, property.getValueRangeSet());

        } else if (Objects.equals(EsPropertyType.DOUBLE, property.getMultiFieldsType())
                || Objects.equals(EsPropertyType.FLOAT, property.getMultiFieldsType())
                || Objects.equals(EsPropertyType.LONG, property.getMultiFieldsType())
                || Objects.equals(EsPropertyType.INTEGER, property.getMultiFieldsType())) {
            return createNumberRangeAggregationBuilder(property.getName(),
                    StringUtils.join(fieldName, DOT, property.getMultiFieldsType().getValue()), property.getValueRangeSet());
        }

        throw new IllegalArgumentException(String.format("Unsupported indexed property type [%s, %s] in aggregation",
                property.getName(), property.getMultiFieldsType()));
    }

    private AggregationBuilder createNumberRangeAggregationBuilder(final String name, final String fieldName,
                                                                   final EsValueRangeSet rangeSet) {
        if (rangeSet != null) {
            final List<EsValueRange> ranges = valueRangeRepository.findByValueRangeSet(rangeSet);
            final EsValueRangeType rangeType = rangeSet.getType();
            if (CollectionUtils.isNotEmpty(ranges)) {
                final RangeAggregationBuilder rangeAggregationBuilder = AggregationBuilders.range(name).field(fieldName);
                for (EsValueRange range : ranges) {
                    final String valueFrom = range.getValueFrom();
                    final String valueTo = range.getValueTo();
                    if (valueFrom != null && valueTo != null) {
                        rangeAggregationBuilder.addRange(range.getName(), parseValue(valueFrom, rangeType),
                                parseValue(valueTo, rangeType));
                    } else if (valueFrom != null) {
                        rangeAggregationBuilder.addUnboundedFrom(range.getName(), parseValue(valueFrom, rangeType));
                    } else if (valueTo != null) {
                        rangeAggregationBuilder.addUnboundedTo(range.getName(), parseValue(valueTo, rangeType));
                    } else {
                        LOGGER.warn("Neither valueFrom nor valueTo has value {}", range);
                    }
                }
                return rangeAggregationBuilder;
            }
        }
        throw new IllegalArgumentException(String.format("Range set is required for range faceted property [%s]", fieldName));
    }

    private TermsAggregationBuilder createTermAggregationBuilder(final EsIndexedProperty property, final String fieldName) {
        final TermsAggregationBuilder builder = createTermAggregationBuilder(property.getName(), fieldName);
        final int maxBuckets = Optional.of(property)
                .map(EsIndexedProperty::getIndex)
                .map(EsIndex::getFacetSearchConfig)
                .map(EsFacetSearchConfig::getSearchConfig)
                .map(EsSearchConfig::getMaxBuckets).get();
        builder.size(maxBuckets);
        return builder;
    }

    private TermsAggregationBuilder createTermAggregationBuilder(final String name, final String fieldName) {
        return AggregationBuilders.terms(name).field(fieldName);
    }

    private double parseValue(final String numberStr, final EsValueRangeType rangeType) {
        if (EsValueRangeType.DOUBLE.equals(rangeType)) {
            return Double.parseDouble(numberStr);
        } else if (EsValueRangeType.FLOAT.equals(rangeType)) {
            return Float.parseFloat(numberStr);
        } else if (EsValueRangeType.LONG.equals(rangeType)) {
            return Long.parseLong(numberStr);
        } else if (EsValueRangeType.INTEGER.equals(rangeType)) {
            return Integer.parseInt(numberStr);
        }
        throw new IllegalArgumentException(String.format("Unsupported range type [%s]", rangeType));
    }
}
