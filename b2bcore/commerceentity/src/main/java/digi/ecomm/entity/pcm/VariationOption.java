package digi.ecomm.entity.pcm;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.sync.SynchronizableEntity;
import digi.ecomm.entity.sync.SynchronizationEmbedding;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "variation_options", uniqueConstraints = {
        @UniqueConstraint(columnNames = {VariationOption.Fields.CODE})}, //NOSONAR
        indexes = {
                @Index(columnList = SynchronizationEmbedding.Fields.EXTERNAL_ID, unique = true)}
)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class VariationOption extends AbstractEntity implements SynchronizableEntity {

    private static final long serialVersionUID = 1L;

    @Embedded
    private SynchronizationEmbedding sync = new SynchronizationEmbedding();

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.VARIATION)
    private Variation variation;

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = VariantProduct.Fields.OPTIONS, fetch = FetchType.LAZY)
    private Set<VariantProduct> variantProducts = new HashSet<>();

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
