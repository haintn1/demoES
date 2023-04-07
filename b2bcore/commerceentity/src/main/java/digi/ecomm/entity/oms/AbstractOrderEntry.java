package digi.ecomm.entity.oms;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.organization.B2BCostCenter;
import digi.ecomm.entity.pcm.Product;
import digi.ecomm.entity.store.PointOfService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public abstract class AbstractOrderEntry extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    protected static final String PRODUCT_ID = "product_id";
    private static final int APPEND_AS_LAST = -1;

    @Column(nullable = false)
    private Integer entryNumber = APPEND_AS_LAST;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PRODUCT)
    @JoinColumn(name = PRODUCT_ID, referencedColumnName = AbstractEntity.Fields.ID)
    private Product product;

    @Column(nullable = false)
    private Long quantity;

    @Column
    private Double basePrice;

    @Column
    private Double totalPrice;

    @Column
    private Boolean calculated;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.COST_CENTER)
    private B2BCostCenter costCenter;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.DELIVERY_POINT_OF_SERVICE)
    private PointOfService deliveryPointOfService;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
