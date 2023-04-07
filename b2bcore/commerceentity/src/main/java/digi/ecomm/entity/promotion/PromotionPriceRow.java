package digi.ecomm.entity.promotion;

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

@Entity
@Table(name = "promotion_price_rows")
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class PromotionPriceRow extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Double price;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CURRENCY)
    private Currency currency;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
