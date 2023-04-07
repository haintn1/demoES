package digi.ecomm.entity.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class DebitCardPaymentInfo extends PaymentInfo {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String bankIDNumber;

    @Column(nullable = false)
    private String bank;

    /**
     * It should be masked.
     */
    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String baOwner;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
