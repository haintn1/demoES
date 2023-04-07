package digi.ecomm.entity.pcm;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.user.UserDiscountGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;


@Entity
@Table(name = "discount_rows", indexes = {
        @Index(columnList = PDTItem.PRODUCT_ID)})
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class DiscountRow extends PDTItem {

    private static final long serialVersionUID = 1L;

    @Column
    private Double value;

    @Column
    private Boolean absolute;

    @Column
    private Boolean asTargetPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.DISCOUNT)
    private Discount discount;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PRODUCT_GROUP)
    private ProductDiscountGroup productGroup;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.USER_GROUP)
    private UserDiscountGroup userGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PRODUCT)
    @JoinColumn(name = PRODUCT_ID, referencedColumnName = AbstractEntity.Fields.ID)
    private Product product;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
