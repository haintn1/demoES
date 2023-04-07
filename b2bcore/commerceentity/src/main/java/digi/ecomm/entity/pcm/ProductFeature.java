package digi.ecomm.entity.pcm;

import digi.ecomm.entity.AbstractEntity;
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
@Table(name = "product_features", indexes = {
        @Index(columnList = SynchronizationEmbedding.Fields.EXTERNAL_ID, unique = true)}
)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class ProductFeature extends AbstractEntity implements SynchronizableEntity {

    private static final long serialVersionUID = 1L;

    @Embedded
    private SynchronizationEmbedding sync = new SynchronizationEmbedding();

    @Column
    private String description;

    @Column(nullable = false)
    private String rawValue;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH}, optional = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.ATTRIBUTE)
    private Attribute attribute;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH}, optional = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.UNIT)
    private AttributeUnit unit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PRODUCT)
    private Product product;

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
