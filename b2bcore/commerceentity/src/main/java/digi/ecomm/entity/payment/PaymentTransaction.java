package digi.ecomm.entity.payment;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.c2l.Currency;
import digi.ecomm.entity.oms.AbstractOrder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment_transactions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {PaymentTransaction.Fields.CODE, PaymentTransaction.ORDER_ID}) //NOSONAR
})
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class PaymentTransaction extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    static final String ORDER_ID = "order_id";

    @Column(nullable = false)
    private String code;

    @Column
    private String requestId;

    @Column
    private String requestToken;

    @Column
    private String paymentProvider;

    @Column
    private BigDecimal plannedAmount;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CURRENCY)
    private Currency currency;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.INFO)
    private PaymentInfo info;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.ORDER)
    @JoinColumn(name = ORDER_ID, referencedColumnName = AbstractEntity.Fields.ID)
    private AbstractOrder order;

    @OneToMany(mappedBy = PaymentTransactionEntry.Fields.PAYMENT_TRANSACTION, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @Setter(AccessLevel.NONE)
    private List<PaymentTransactionEntry> entries = new ArrayList<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
