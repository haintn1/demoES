package digi.ecomm.commercesearch.search.populator;

import digi.ecomm.commercesearch.data.IndexedPropertyGroup;
import digi.ecomm.commercesearch.repository.EsBoostRuleRepository;
import digi.ecomm.commercesearch.search.data.SearchQueryPageableData;
import digi.ecomm.commercesearch.strategy.EsBoostRuleFilterBuilderFactory;
import digi.ecomm.commercesearch.strategy.EsIndexedPropertyGroupStrategy;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.entity.commercesearch.EsPropertyType;
import digi.ecomm.entity.commercesearch.advance.EsBoostRule;
import digi.ecomm.entity.commercesearch.advance.EsBoostRulesMergeMode;
import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.util.ServicesUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SearchSourceBuilderBoostRulePopulator implements Populator<SearchQueryPageableData, SearchSourceBuilder> {
    private final EsBoostRuleRepository boostRuleRepository;
    private final EsBoostRuleFilterBuilderFactory boostRuleFilterBuilderFactory;
    private final EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy;

    public SearchSourceBuilderBoostRulePopulator(
            final EsBoostRuleRepository boostRuleRepository,
            final EsBoostRuleFilterBuilderFactory boostRuleFilterBuilderFactory,
            final EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy) {
        this.boostRuleRepository = boostRuleRepository;
        this.boostRuleFilterBuilderFactory = boostRuleFilterBuilderFactory;
        this.indexedPropertyGroupStrategy = indexedPropertyGroupStrategy;
    }

    @Override
    public void populate(final SearchQueryPageableData source, final SearchSourceBuilder target) throws ConversionException {
        ServicesUtils.validateParameterNotNullStandardMessage("source", source);

        final List<EsBoostRule> boostRules =
                boostRuleRepository.findByAdvancedSearchConfig(source.getContext().getAdvancedSearchConfig());
        if (CollectionUtils.isNotEmpty(boostRules)) {
            final List<IndexedPropertyGroup> indexedPropertyGroup =
                    indexedPropertyGroupStrategy.group(source.getContext().getIndexedProperties());

            if (CollectionUtils.isNotEmpty(indexedPropertyGroup)) {
                final List<FunctionScoreQueryBuilder.FilterFunctionBuilder> boostingFilters = new ArrayList<>();

                // Filter on the field itself
                final Map<String, EsIndexedProperty> normalIndexedPropertyMap = indexedPropertyGroup.stream()
                        .map(IndexedPropertyGroup::getProperty)
                        .filter(property -> !Objects.equals(property.getType(), EsPropertyType.NESTED))
                        .collect(Collectors.toMap(EsIndexedProperty::getName, Function.identity()));
                boostingFilters.addAll(boostRules.stream()
                        .filter(boostRule -> Objects.nonNull(normalIndexedPropertyMap.get(boostRule.getIndexProperty())))
                        .map(boostRule -> {
                            final QueryBuilder functionFilter = boostRuleFilterBuilderFactory.get(
                                    normalIndexedPropertyMap.get(boostRule.getIndexProperty()), boostRule);
                            return new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                    functionFilter, ScoreFunctionBuilders.weightFactorFunction(boostRule.getBoost()));
                        })
                        .collect(Collectors.toList()));

                // Filter on the nested field
                final Map<String, EsIndexedProperty> nestedIndexedPropertyMap = indexedPropertyGroup.stream()
                        .flatMap(nestedProperty -> nestedProperty.getNestedProperties().stream())
                        .collect(Collectors.toMap(EsIndexedProperty::getName, Function.identity()));
                boostingFilters.addAll(boostRules.stream()
                        .filter(boostRule -> Objects.nonNull(nestedIndexedPropertyMap.get(boostRule.getIndexProperty())))
                        .map(boostRule -> {
                            final QueryBuilder nestedFunctionFilter = boostRuleFilterBuilderFactory.get(
                                    nestedIndexedPropertyMap.get(boostRule.getIndexProperty()), boostRule);
                            final QueryBuilder functionFilter = QueryBuilders.nestedQuery(
                                    nestedIndexedPropertyMap.get(boostRule.getIndexProperty()).getOuterProperty().getName(),
                                    nestedFunctionFilter, ScoreMode.None);
                            return new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                    functionFilter, ScoreFunctionBuilders.weightFactorFunction(boostRule.getBoost()));
                        })
                        .collect(Collectors.toList()));

                final FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(target.query(),
                        boostingFilters.stream().toArray(FunctionScoreQueryBuilder.FilterFunctionBuilder[]::new));
                functionScoreQueryBuilder.scoreMode(FunctionScoreQuery.ScoreMode.MAX);
                functionScoreQueryBuilder.boostMode(getBoostMode(source.getContext().getAdvancedSearchConfig().getBoostRulesMergeMode()));
                target.query(functionScoreQueryBuilder);
            }
        }
    }

    private CombineFunction getBoostMode(final EsBoostRulesMergeMode boostRulesMergeMode) {
        if (Objects.equals(EsBoostRulesMergeMode.ADD, boostRulesMergeMode)) {
            return CombineFunction.SUM;
        }
        if (Objects.equals(EsBoostRulesMergeMode.REPLACE, boostRulesMergeMode)) {
            return CombineFunction.REPLACE;
        }
        return CombineFunction.SUM;
    }
}
