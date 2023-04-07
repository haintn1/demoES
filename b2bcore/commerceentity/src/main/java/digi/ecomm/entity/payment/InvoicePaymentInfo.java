package digi.ecomm.entity.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class InvoicePaymentInfo extends PaymentInfo {

    private static final long serialVersionUID = 1L;
}
