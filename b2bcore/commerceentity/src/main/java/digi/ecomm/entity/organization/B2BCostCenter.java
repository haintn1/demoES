package digi.ecomm.entity.organization;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.c2l.Currency;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "b2b_cost_centers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {B2BCostCenter.Fields.CODE})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class B2BCostCenter extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String code;

    @Column
    private String name;

    @Column(nullable = false)
    private Boolean active = Boolean.TRUE;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CURRENCY)
    private Currency currency;

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = B2BBudget.Fields.COST_CENTERS)
    private Set<B2BBudget> budgets = new HashSet<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
