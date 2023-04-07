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
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EsSearchConfig extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String SEARCH_CONFIG_PATH = "search-config";

    @Column(nullable = false)
    private Integer pageSize;

    @Column
    private String description;

    @Column(nullable = false)
    private boolean enableHighlighting = false;

    @Column(nullable = false)
    private int maxBuckets = 10;

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
