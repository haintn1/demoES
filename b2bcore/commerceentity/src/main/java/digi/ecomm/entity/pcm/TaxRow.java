package digi.ecomm.entity.pcm;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.user.UserTaxGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "tax_rows", indexes = {
        @Index(columnList = PDTItem.PRODUCT_ID)})
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class TaxRow extends PDTItem {

    private static final long serialVersionUID = 1L;

    @Column
    private Double value;

    @Column
    private Boolean absolute;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.TAX)
    private Tax tax;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PRODUCT_GROUP)
    private ProductTaxGroup productGroup;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PRODUCT_GROUP)
    private UserTaxGroup userGroup;

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
