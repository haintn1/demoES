package digi.ecomm.entity.promotion;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class ProductBundlePromotion extends ProductPromotion {

    private static final long serialVersionUID = 1L;

    /**
     * Fixed price for whole bundle in specific currencies.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE},
            orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<PromotionPriceRow> bundlePrices = new ArrayList<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
