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
public class OrderThresholdPartnerPromotion extends OrderPromotion {

    private static final long serialVersionUID = 1L;

    /**
     * Flag to indicate if the discounted price of the product is allowed to count towards the threshold,
     * otherwise it does not count towards the threshold.
     */
    @Column
    private Boolean includeDiscountedPriceInThreshold;

    /**
     * The cart total value threshold in specific currencies.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE},
            orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<PromotionPriceRow> thresholdTotals = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.DISCOUNT_PRODUCT)
    private Product discountProduct;

    /**
     * Fixed price for discounted product in specific currencies.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE},
            orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<PromotionPriceRow> productPrices = new ArrayList<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
