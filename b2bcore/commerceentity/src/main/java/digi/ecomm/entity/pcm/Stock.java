package digi.ecomm.entity.pcm;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.store.Warehouse;
import digi.ecomm.entity.sync.SynchronizableEntity;
import digi.ecomm.entity.sync.SynchronizationEmbedding;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "stocks", indexes = {
        @Index(columnList = Stock.Fields.PRODUCT_CODE), //NOSONAR
        @Index(columnList = SynchronizationEmbedding.Fields.EXTERNAL_ID, unique = true)}
)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Stock extends AbstractEntity implements SynchronizableEntity {

    private static final long serialVersionUID = 1L;

    @Embedded
    private SynchronizationEmbedding sync = new SynchronizationEmbedding();

    @Column(nullable = false)
    private int available;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private int reserved;

    @Column(nullable = false)
    private int preOrder;

    @Column(nullable = false)
    private int maxPreOrder;

    @Column
    @Enumerated(EnumType.STRING)
    private InStockStatus inStockStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.WAREHOUSE)
    private Warehouse warehouse;

    @Override
    public SynchronizationEmbedding getSync() {
        return sync;
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
