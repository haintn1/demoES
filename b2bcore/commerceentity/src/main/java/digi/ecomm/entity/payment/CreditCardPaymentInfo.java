package digi.ecomm.entity.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class CreditCardPaymentInfo extends PaymentInfo {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String ccOwner;

    /**
     * It should be masked.
     */
    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CreditCardType type;

    @Column(nullable = false)
    private String validToMonth;

    @Column(nullable = false)
    private String validToYear;

    @Column
    private String validFromMonth;

    @Column
    private String validFromYear;

    /**
     * The reference information for the credit cart data stored in the external payment provider.
     */
    @Column
    private String subscriptionId;

    @Column
    private Boolean subscriptionValidated;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
