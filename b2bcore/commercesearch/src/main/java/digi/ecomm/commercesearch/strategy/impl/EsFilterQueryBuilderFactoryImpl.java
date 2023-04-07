package digi.ecomm.commercesearch.strategy.impl;

import digi.ecomm.commercesearch.provider.FieldNameResolver;
import digi.ecomm.commercesearch.search.data.FilterQueryOperator;
import digi.ecomm.commercesearch.search.data.SearchFilterQueryData;
import digi.ecomm.commercesearch.strategy.EsFilterQueryBuilderFactory;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.entity.commercesearch.EsPropertyType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;

import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
public class EsFilterQueryBuilderFactoryImpl implements EsFilterQueryBuilderFactory {

    private static final String DOT = ".";
    private static final String DASH = "-";

    private final FieldNameResolver fieldNameResolver;

    @Override
    public QueryBuilder get(final EsIndexedProperty property, final SearchFilterQueryData filter) {
        final String fieldName = fieldNameResolver.resolve(property);

        if (Objects.equals(EsPropertyType.KEYWORD, property.getType())) {
            return createTermFilterQueryBuilder(fieldName, filter.getOperator(), filter.getValues());

        } else if (Objects.equals(EsPropertyType.KEYWORD, property.getMultiFieldsType())) {
            return createTermFilterQueryBuilder(StringUtils.join(fieldName, DOT, property.getMultiFieldsType().getValue()),
                    filter.getOperator(), filter.getValues());

        } else if (Objects.equals(EsPropertyType.DOUBLE, property.getType())
                || Objects.equals(EsPropertyType.FLOAT, property.getType())
                || Objects.equals(EsPropertyType.LONG, property.getType())
                || Objects.equals(EsPropertyType.INTEGER, property.getType())) {
            return createNumberRangeFilterQueryBuilder(fieldName, filter.getOperator(), filter.getValues());

        } else if (Objects.equals(EsPropertyType.DOUBLE, property.getMultiFieldsType())
                || Objects.equals(EsPropertyType.FLOAT, property.getMultiFieldsType())
                || Objects.equals(EsPropertyType.LONG, property.getMultiFieldsType())
                || Objects.equals(EsPropertyType.INTEGER, property.getMultiFieldsType())) {
            return createNumberRangeFilterQueryBuilder(fieldName, filter.getOperator(), filter.getValues());
        }

        throw new IllegalArgumentException(String.format("Unsupported indexed property type [%s, %s] in filtering",
                property.getType(), property.getMultiFieldsType()));
    }

    private QueryBuilder createTermFilterQueryBuilder(final String fieldName, final FilterQueryOperator operator,
                                                      final Set<String> values) {
        if (Objects.equals(FilterQueryOperator.AND, operator)) {
            final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            values.stream()
                    .map(value -> QueryBuilders.termQuery(fieldName, value))
                    .forEach(boolQueryBuilder::must);
            return boolQueryBuilder;

        } else if (Objects.equals(FilterQueryOperator.OR, operator)) {
            return QueryBuilders.termsQuery(fieldName, values.stream().toArray(String[]::new));
        }

        throw new IllegalArgumentException(String.format("Unsupported filter operator [%s]", operator));
    }

    private QueryBuilder createNumberRangeFilterQueryBuilder(final String fieldName, final FilterQueryOperator operator,
                                                             final Set<String> values) {
        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        values.stream()
                .map(value -> {
                    final RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(fieldName);
                    final String[] valueArr = StringUtils.split(value, DASH);
                    if (valueArr.length >= 2) {
                        rangeQueryBuilder.gte(valueArr[0]).lt(valueArr[1]);
                    }

                    if (valueArr.length == 1) {
                        if (StringUtils.endsWith(value, DASH)) {
                            // From
                            rangeQueryBuilder.gte(valueArr[0]);

                        } else if (StringUtils.startsWith(value, DASH)) {
                            // To
                            rangeQueryBuilder.lt(valueArr[0]);
                        }
                    }

                    return rangeQueryBuilder;
                })
                .forEach(queryBuilder -> {
                    if (FilterQueryOperator.AND.equals(operator)) {
                        boolQueryBuilder.must(queryBuilder);
                    } else if (FilterQueryOperator.OR.equals(operator)) {
                        boolQueryBuilder.should(queryBuilder);
                    } else {
                        throw new IllegalArgumentException(String.format("Unsupported filter operator [%s]", operator));
                    }
                });
        return boolQueryBuilder;
    }
}
