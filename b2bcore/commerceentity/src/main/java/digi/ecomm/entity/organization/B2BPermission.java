package digi.ecomm.entity.organization;

import digi.ecomm.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "b2b_permissions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {B2BPermission.Fields.CODE}) //NOSONAR
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public abstract class B2BPermission extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Boolean active = Boolean.TRUE;

    @Column
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.UNIT)
    private B2BUnit unit;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
