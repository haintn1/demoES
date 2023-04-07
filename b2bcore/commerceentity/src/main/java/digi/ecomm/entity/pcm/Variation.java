package digi.ecomm.entity.pcm;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.sync.SynchronizableEntity;
import digi.ecomm.entity.sync.SynchronizationEmbedding;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "variations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {Variation.Fields.CODE})}, //NOSONAR
        indexes = {
                @Index(columnList = SynchronizationEmbedding.Fields.EXTERNAL_ID, unique = true)} //NOSONAR
)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Variation extends AbstractEntity implements SynchronizableEntity {

    private static final long serialVersionUID = 1L;

    @Embedded
    private SynchronizationEmbedding sync = new SynchronizationEmbedding();

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = VariationOption.Fields.VARIATION, fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<VariationOption> options = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = Product.Fields.VARIATIONS, fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

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
