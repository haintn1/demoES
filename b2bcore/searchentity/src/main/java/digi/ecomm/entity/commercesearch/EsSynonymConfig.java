package digi.ecomm.entity.commercesearch;

import digi.ecomm.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EsSynonymConfig extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String SYNONYM_CONFIGS_PATH = "synonym-configs";

    @Column(nullable = false)
    private String synonymFrom;

    @Column
    private String synonymTo;

    @ManyToOne(fetch = FetchType.LAZY)
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
