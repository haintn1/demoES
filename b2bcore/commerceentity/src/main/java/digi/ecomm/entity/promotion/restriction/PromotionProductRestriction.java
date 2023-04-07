package digi.ecomm.entity.promotion.restriction;

import digi.ecomm.entity.pcm.Product;
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
public class PromotionProductRestriction extends AbstractPromotionRestriction {

    private static final long serialVersionUID = 1L;

    /**
     * The products that the promotion should not be applied to.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    private List<Product> products = new ArrayList<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
