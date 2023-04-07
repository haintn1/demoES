package digi.ecomm.entity.promotion;

import digi.ecomm.entity.pcm.Product;
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
public class OrderThresholdFreeGiftPromotion extends OrderPromotion {

    private static final long serialVersionUID = 1L;

    /**
     * The cart total value threshold in specific currencies.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE},
            orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<PromotionPriceRow> thresholdTotals = new ArrayList<>();

    /**
     * The free gift product to add to the cart.
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.GIFT_PRODUCT)
    private Product giftProduct;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
