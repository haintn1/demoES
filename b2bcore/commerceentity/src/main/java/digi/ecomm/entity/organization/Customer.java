package digi.ecomm.entity.organization;

import digi.ecomm.entity.oms.Cart;
import digi.ecomm.entity.oms.Order;
import digi.ecomm.entity.oms.Quote;
import digi.ecomm.entity.payment.PaymentInfo;
import digi.ecomm.entity.user.Address;
import digi.ecomm.entity.user.Title;
import digi.ecomm.entity.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public abstract class Customer extends User {

    private static final long serialVersionUID = 1L;

    @Column
    private String phoneNumber;

    /**
     * Attribute is used during forgotten password to ensure that the link can be used only once.
     */
    @Column
    private String forgottenPasswordToken;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Address.Fields.TITLE)
    private Title title;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.DEFAULT_PAYMENT_INFO)
    private PaymentInfo defaultPaymentInfo;

    @OneToMany(mappedBy = PaymentInfo.Fields.USER, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @Setter(AccessLevel.NONE)
    private List<PaymentInfo> paymentInfos = new ArrayList<>();

    @OneToMany(mappedBy = Cart.Fields.CUSTOMER, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @Setter(AccessLevel.NONE)
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = Quote.Fields.CUSTOMER, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @Setter(AccessLevel.NONE)
    private List<Quote> quotes = new ArrayList<>();

    @OneToMany(mappedBy = Order.Fields.CUSTOMER, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @Setter(AccessLevel.NONE)
    private List<Order> orders = new ArrayList<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
