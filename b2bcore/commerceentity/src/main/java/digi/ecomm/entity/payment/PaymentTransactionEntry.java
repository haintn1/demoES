package digi.ecomm.entity.payment;

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

@Entity
@Table(name = "payment_transaction_entries", uniqueConstraints = {
        @UniqueConstraint(columnNames = {PaymentTransactionEntry.Fields.CODE, PaymentTransactionEntry.Fields.TYPE, //NOSONAR
                PaymentTransactionEntry.PAYMENT_TRANSACTION_ID}) //NOSONAR
})
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class PaymentTransactionEntry extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    static final String PAYMENT_TRANSACTION_ID = "payment_transaction_id";

    @Column(nullable = false)
    private String code;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentTransactionType type;

    @Column
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CURRENCY)
    private Currency currency;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Column
    private String transactionStatus;

    @Column
    private String transactionStatusDetails;

    @Column
    private String requestToken;

    @Column
    private String requestId;

    @Column
    private String subscriptionID;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PAYMENT_TRANSACTION)
    @JoinColumn(name = PAYMENT_TRANSACTION_ID, referencedColumnName = AbstractEntity.Fields.ID)
    private PaymentTransaction paymentTransaction;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
