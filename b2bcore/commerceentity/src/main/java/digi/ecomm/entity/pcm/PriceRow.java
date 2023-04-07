package digi.ecomm.entity.pcm;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.user.UserPriceGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;


@Entity
@Table(name = "price_rows", indexes = {
        @Index(columnList = PDTItem.PRODUCT_ID)})
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class PriceRow extends PDTItem {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Long minqtd = 1L;

    @Column(nullable = false)
    private Boolean net = Boolean.FALSE;

    @Column(nullable = false)
    private Double price;

    @Column
    private Boolean giveAwayPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PRODUCT_GROUP)
    private ProductPriceGroup productGroup;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.USER_GROUP)
    private UserPriceGroup userGroup;

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
