package digi.ecomm.entity.commercesearch.advance;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EsAdvancedSearchConfig extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String ADVANCED_SEARCH_CONFIG_PATH = "advanced-search-config";
    public static final String ADVANCED_SEARCH_CONFIGS_PATH = "advanced-search-configs";

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private EsBoostRulesMergeMode boostRulesMergeMode;

    @OneToMany(mappedBy = EsBoostRule.Fields.ADVANCED_SEARCH_CONFIG, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    @RestResource(path = EsBoostRule.BOOST_RULES_PATH)
    private List<EsBoostRule> boostRules = new ArrayList<>();

    @OneToMany(mappedBy = EsPromotedItem.Fields.ADVANCED_SEARCH_CONFIG, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    @RestResource(path = EsPromotedItem.PROMOTED_ITEMS_PATH)
    private List<EsPromotedItem> promotedItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.FACET_SEARCH_CONFIG)
    @RestResource(path = EsFacetSearchConfig.FACET_SEARCH_CONFIG_PATH)
    private EsFacetSearchConfig facetSearchConfig;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
