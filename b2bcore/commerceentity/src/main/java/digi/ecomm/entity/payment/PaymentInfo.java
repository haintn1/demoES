package digi.ecomm.entity.payment;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.user.Address;
import digi.ecomm.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "payment_infos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class PaymentInfo extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String code;

    /**
     * This attribute is to distinguish between the 'normal' created paymentinfo and the cloned ones.
     */
    @Column
    private Boolean duplicate;

    @Column
    private Boolean visibleInPaymentBook;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.ORIGINAL)
    private PaymentInfo original;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.USER)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.BILLING_ADDRESS)
    private Address billingAddress;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
