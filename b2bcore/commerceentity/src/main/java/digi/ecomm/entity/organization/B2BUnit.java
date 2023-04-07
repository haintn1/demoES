package digi.ecomm.entity.organization;

import digi.ecomm.entity.oms.Order;
import digi.ecomm.entity.oms.Quote;
import digi.ecomm.entity.user.Employee;
import digi.ecomm.entity.user.Principal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "b2b_units", uniqueConstraints = {
        @UniqueConstraint(columnNames = {Principal.Fields.UID})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class B2BUnit extends Company {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = Quote.Fields.CUSTOMER, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @Setter(AccessLevel.NONE)
    private List<Quote> quotes = new ArrayList<>();

    @OneToMany(mappedBy = Order.Fields.CUSTOMER, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @Setter(AccessLevel.NONE)
    private List<Order> orders = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private Employee accountManager;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "b2b_unit_2_approver",
            joinColumns = @JoinColumn(name = "b2bunit_id"),
            inverseJoinColumns = @JoinColumn(name = "approver_id"))
    private Set<B2BCustomer> approvers = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @Setter(AccessLevel.NONE)
    private List<B2BCostCenter> costCenters = new ArrayList<>();

    /**
     * Minimum amount on cart for qualifying for quote request.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private B2BQuoteLimit quoteLimit;

    @OneToMany(mappedBy = B2BPermission.Fields.UNIT, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    private List<B2BPermission> permissions = new ArrayList<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
