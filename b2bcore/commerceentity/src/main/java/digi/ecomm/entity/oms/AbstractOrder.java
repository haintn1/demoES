package digi.ecomm.entity.oms;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.c2l.Currency;
import digi.ecomm.entity.payment.PaymentInfo;
import digi.ecomm.entity.payment.PaymentTransaction;
import digi.ecomm.entity.pcm.Discount;
import digi.ecomm.entity.store.BaseStore;
import digi.ecomm.entity.user.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public abstract class AbstractOrder extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String code;

    @Column
    private Boolean calculated;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column
    private Double deliveryCost;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationTime;

    @Column
    private Boolean net;

    @Column
    private Double paymentCost;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated
    private OrderStatus orderStatus;

    @Column
    private Double totalPrice;

    @Column
    private Double totalDiscounts;

    @Column
    private Double totalTax;

    @Column
    private Double subtotal;

    @Column
    private Boolean discountsIncludeDeliveryCost;

    @Column
    private Boolean discountsIncludePaymentCost;

    @Column
    private String deliveryNote;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CURRENCY)
    private Currency currency;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.DELIVERY_ADDRESS)
    private Address deliveryAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PAYMENT_ADDRESS)
    private Address paymentAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PAYMENT_INFO)
    private PaymentInfo paymentInfo;

    @OneToMany(mappedBy = PaymentTransaction.Fields.ORDER, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    private List<PaymentTransaction> paymentTransactions = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.STORE)
    private BaseStore store;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "order_2_discount",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "discount_id")
    )
    private Set<Discount> discounts = new HashSet<>();

    /**
     * Get entries.
     *
     * @param <T>
     * @return
     */
    public abstract <T extends AbstractOrderEntry> List<T> getEntries();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
