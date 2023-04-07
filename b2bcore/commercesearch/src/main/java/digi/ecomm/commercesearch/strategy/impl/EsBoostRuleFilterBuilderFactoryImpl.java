package digi.ecomm.commercesearch.strategy.impl;

import com.google.common.base.Preconditions;
import digi.ecomm.commercesearch.strategy.EsBoostRuleFilterBuilderFactory;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.entity.commercesearch.EsPropertyType;
import digi.ecomm.entity.commercesearch.advance.EsBoostOperator;
import digi.ecomm.entity.commercesearch.advance.EsBoostRule;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.Objects;

public class EsBoostRuleFilterBuilderFactoryImpl implements EsBoostRuleFilterBuilderFactory {
    private static final String DOT = ".";

    @Override
    public QueryBuilder get(final EsIndexedProperty property, final EsBoostRule boostRule) {
        Preconditions.checkArgument(StringUtils.equals(property.getName(), boostRule.getIndexProperty()),
                "The boosted field does not match the indexed one");

        if (Objects.equals(EsPropertyType.KEYWORD, property.getType())) {
            return createTermFilter(boostRule.getIndexProperty(), boostRule.getOperator(), boostRule.getValue());
        } else if (Objects.equals(EsPropertyType.KEYWORD, property.getMultiFieldsType())) {
            return createTermFilter(StringUtils.join(property.getName(), DOT, property.getMultiFieldsType().getValue()),
                    boostRule.getOperator(), boostRule.getValue());
        }
        throw new IllegalArgumentException(String.format("Unsupported indexed property type [%s, %s]",
                property.getType(), property.getMultiFieldsType()));
    }

    private QueryBuilder createTermFilter(final String fieldName, final EsBoostOperator operator, final String value) {
        if (Objects.isNull(operator) || Objects.equals(EsBoostOperator.EQUAL, operator)) {
            return QueryBuilders.termQuery(fieldName, value);
        }
        throw new IllegalArgumentException(String.format("Unsupported filter operator [%s]", operator));
    }
}
