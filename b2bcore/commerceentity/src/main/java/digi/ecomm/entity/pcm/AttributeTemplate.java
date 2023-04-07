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
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attribute_templates", uniqueConstraints = {
        @UniqueConstraint(columnNames = {AttributeTemplate.Fields.NAME})}, //NOSONAR
        indexes = {
                @Index(columnList = SynchronizationEmbedding.Fields.EXTERNAL_ID, unique = true)}
)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class AttributeTemplate extends AbstractEntity implements SynchronizableEntity {

    private static final long serialVersionUID = 1L;

    @Embedded
    private SynchronizationEmbedding sync = new SynchronizationEmbedding();

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "attribue_template_2_attribute",
            joinColumns = @JoinColumn(name = "template_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private Set<Attribute> attributes = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = Category.Fields.ATTRIBUTE_TEMPLATES, fetch = FetchType.LAZY)
    private Set<Category> categories = new HashSet<>();

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
