package digi.ecomm.entity.pcm;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.c2l.Currency;
import digi.ecomm.entity.catalog.Catalog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(indexes = {
        @Index(columnList = PDTItem.CATALOG_ID)
})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public abstract class PDTItem extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    protected static final String PRODUCT_ID = "product_id";
    protected static final String CATALOG_ID = "catalog_id";

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CURRENCY)
    private Currency currency;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CATALOG)
    @JoinColumn(name = CATALOG_ID, referencedColumnName = AbstractEntity.Fields.ID)
    private Catalog catalog;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
