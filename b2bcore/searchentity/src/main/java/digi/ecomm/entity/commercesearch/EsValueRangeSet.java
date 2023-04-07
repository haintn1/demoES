package digi.ecomm.entity.commercesearch;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.commercesearch.converter.ValueRangeTypeConverter;
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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {EsValueRangeSet.Fields.NAME})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EsValueRangeSet extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String VALUE_RANGE_SET_PATH = "value-range-set";
    public static final String VALUE_RANGE_SETS_PATH = "value-range-sets";

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Convert(converter = ValueRangeTypeConverter.class)
    private EsValueRangeType type;

    @Column
    private String qualifier;

    @OneToMany(mappedBy = EsValueRange.Fields.VALUE_RANGE_SET, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    @RestResource(path = EsValueRange.VALUE_RANGES_PATH)
    private List<EsValueRange> valueRanges = new ArrayList<>();

    @OneToOne(mappedBy = EsIndexedProperty.Fields.VALUE_RANGE_SET, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.INDEXED_PROPERTY)
    @RestResource(path = EsIndexedProperty.INDEXED_PROPERTY_PATH)
    private EsIndexedProperty indexedProperty;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
