package digi.ecomm.entity.commercesearch;

import digi.ecomm.entity.AbstractEntity;
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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {EsIndex.Fields.NAME}), //NOSONAR
        @UniqueConstraint(columnNames = {EsIndex.Fields.INDEXED_ENTITY_TYPE, EsIndex.FACET_SEARCH_CONFIG_ID}) //NOSONAR
})
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EsIndex extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    static final String FACET_SEARCH_CONFIG_ID = "facet_search_config_id";
    public static final String INDICES_PATH = "indices";
    public static final String INDEX_PATH = "index";

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EsIndexedEntityType indexedEntityType;

    @OneToMany(mappedBy = EsSort.Fields.INDEX, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    @RestResource(path = EsSort.SORTS_PATH)
    private List<EsSort> sorts = new ArrayList<>();

    @OneToMany(mappedBy = EsIndexedProperty.Fields.INDEX, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    @RestResource(path = EsIndexedProperty.INDEXED_PROPERTIES_PATH)
    private List<EsIndexedProperty> indexedProperties = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.FACET_SEARCH_CONFIG)
    @JoinColumn(name = FACET_SEARCH_CONFIG_ID, referencedColumnName = AbstractEntity.Fields.ID)
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
