package digi.ecomm.entity.organization;

import digi.ecomm.entity.user.Principal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "b2b_customers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {Principal.Fields.UID})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class B2BCustomer extends Customer {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean active;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.DEFAULT_B2_B_UNIT)
    private B2BUnit defaultB2BUnit;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
