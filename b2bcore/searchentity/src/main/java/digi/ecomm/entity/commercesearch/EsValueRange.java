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

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {EsValueRange.Fields.NAME, EsValueRange.VALUE_RANGE_SET_ID}) //NOSONAR
})
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EsValueRange extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    static final String VALUE_RANGE_SET_ID = "value_range_set_id";
    public static final String VALUE_RANGES_PATH = "value-ranges";

    @Column(nullable = false)
    private String name;

    /**
     * The lower bound, inclusive.
     */
    @Column
    private String valueFrom;

    /**
     * The upper bound, exclusive.
     */
    @Column
    private String valueTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.VALUE_RANGE_SET)
    @JoinColumn(name = VALUE_RANGE_SET_ID, referencedColumnName = AbstractEntity.Fields.ID)
    @RestResource(path = EsValueRangeSet.VALUE_RANGE_SET_PATH)
    private EsValueRangeSet valueRangeSet;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
