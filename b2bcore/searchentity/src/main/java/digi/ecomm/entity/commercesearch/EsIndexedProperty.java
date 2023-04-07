package digi.ecomm.entity.commercesearch;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.commercesearch.converter.IndexPropertyTypeConverter;
import digi.ecomm.entity.commercesearch.converter.IndexedPropertyFacetTypeConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {EsIndexedProperty.Fields.NAME, EsIndexedProperty.INDEX_ID}) //NOSONAR
})
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EsIndexedProperty extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String INDEXED_PROPERTIES_PATH = "indexed-properties";
    public static final String OUTER_PROPERTY_PATH = "outer-property";
    public static final String INDEXED_PROPERTY_PATH = "indexed-property";

    static final String INDEX_ID = "index_id";

    @Column(nullable = false)
    private String name;

    /**
     * The display name for this property in the list of facet.
     */
    @Column
    private String displayName;

    @Column(nullable = false)
    @Convert(converter = IndexPropertyTypeConverter.class)
    private EsPropertyType type;

    /**
     * Use to model multi fields feature of Elasticsearch. Currently, only support one field.
     */
    @Column
    @Convert(converter = IndexPropertyTypeConverter.class)
    private EsPropertyType multiFieldsType;

    @Column(nullable = false)
    private boolean facet;

    @Column
    private String fieldNameProvider;

    @Column
    private String facetDisplayNameProvider;

    @Column
    @Convert(converter = IndexedPropertyFacetTypeConverter.class)
    private EsIndexedPropertyFacetType facetType;

    /**
     * Whether the property should be included in the list of facets.
     */
    @Column
    private Boolean includeInFacets = Boolean.TRUE;

    /**
     * The ordering of this property in the list of facets. Higher number ordered first.
     */
    @Column
    private int priority;

    /**
     * Date format pattern. Only apply for property of date type.
     */
    @Column
    private String format;

    @Column
    private String fieldValueProvider;

    /**
     * Whether field values are indexed. Fields that are not indexed are not queryable.
     */
    @Column(nullable = false)
    private boolean indexed = true;

    @Column(nullable = false)
    private boolean includeInResponse = true;

    /**
     * Determines if this property will be used for highlighting search term.
     */
    @Column(nullable = false)
    private Boolean useForHighlighting = Boolean.FALSE;

    /**
     * Determines if this property will be used for completion suggestion.
     */
    @Column(nullable = false)
    private Boolean useForAutoCompletion = Boolean.FALSE;

    @Column
    private boolean ftsQuery;

    @Column
    private Float ftsQueryBoost;

    @Column
    private boolean ftsFuzzyQuery;

    @Column
    private Integer ftsFuzzyQueryFuzziness;

    /**
     * Use to model nested property. Only set this value if the parent's index type is {@link EsPropertyType.NESTED}.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.OUTER_PROPERTY)
    @RestResource(path = OUTER_PROPERTY_PATH)
    private EsIndexedProperty outerProperty;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.VALUE_RANGE_SET)
    @RestResource(path = EsValueRangeSet.VALUE_RANGE_SET_PATH)
    private EsValueRangeSet valueRangeSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.INDEX)
    @JoinColumn(name = INDEX_ID, referencedColumnName = AbstractEntity.Fields.ID)
    @RestResource(path = EsIndex.INDEX_PATH)
    private EsIndex index;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
