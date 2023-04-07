package digi.ecomm.entity.organization;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.c2l.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "b2b_budgets", uniqueConstraints = {
        @UniqueConstraint(columnNames = {B2BBudget.Fields.CODE})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class B2BBudget extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String code;

    @Column
    private String name;

    @Column(nullable = false)
    private BigDecimal budget;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(B2BCostCenter.Fields.CURRENCY)
    private Currency currency;

    @Column(nullable = false)
    private Boolean active = Boolean.TRUE;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date activeFrom;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date activeTo;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "budget_2_cost_center",
            joinColumns = @JoinColumn(name = "budget_id"),
            inverseJoinColumns = @JoinColumn(name = "cost_center_id")
    )
    private Set<B2BCostCenter> costCenters = new HashSet<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
