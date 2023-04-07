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
        @UniqueConstraint(columnNames = {EsSortField.Fields.FIELD_NAME, EsSortField.Fields.ASCENDING})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EsSortField extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String SORT_FIELDS_PATH = "sort-fields";

    @Column(nullable = false)
    private String fieldName;

    @Column(nullable = false)
    private Boolean ascending = Boolean.TRUE;

    @Column
    private String fieldNameProvider;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.SORT)
    @RestResource(path = EsSort.SORT_PATH)
    private EsSort sort;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
