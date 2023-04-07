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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {EsSort.Fields.CODE})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EsSort extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String SORTS_PATH = "sorts";
    public static final String SORT_PATH = "sort";

    /**
     * The unique code used to identify the sort.
     */
    @Column(nullable = false)
    private String code;

    /**
     * The display name for this sort.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The property specifies whether a sort should apply boost rules.
     */
    @Column(nullable = false)
    private Boolean useBoost = Boolean.FALSE;

    @OneToMany(mappedBy = EsSortField.Fields.SORT, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    @RestResource(path = EsSortField.SORT_FIELDS_PATH)
    private List<EsSortField> sortFields = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.INDEX)
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
