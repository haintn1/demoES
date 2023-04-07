package digi.ecomm.entity.promotion;

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
public class ProductBXGYFPromotion extends ProductPromotion {

    private static final long serialVersionUID = 1L;

    /**
     * The number of products required in the cart to activate the promotion.
     */
    @Column
    private Integer qualifyingCount = 2;

    /**
     * The number of products within the cart to give away free.
     */
    @Column
    private Integer freeCount = 1;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
