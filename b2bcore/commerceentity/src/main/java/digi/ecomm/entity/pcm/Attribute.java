package digi.ecomm.entity.pcm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.pcm.converter.AttributeTypeConverter;
import digi.ecomm.entity.sync.SynchronizableEntity;
import digi.ecomm.entity.sync.SynchronizationEmbedding;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "attributes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {Attribute.Fields.NAME})}, //NOSONAR
        indexes = {
                @Index(columnList = SynchronizationEmbedding.Fields.EXTERNAL_ID, unique = true)} //NOSONAR
)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Attribute extends AbstractEntity implements SynchronizableEntity {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Embedded
    private SynchronizationEmbedding sync;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    @Convert(converter = AttributeTypeConverter.class)
    private AttributeType type;

    @Column
    private String description;

    @Column
    private Boolean required;

    @Column
    private Boolean enabled;

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = AttributeTemplate.Fields.ATTRIBUTES, fetch = FetchType.LAZY)
    private Set<AttributeTemplate> templates = new HashSet<>();

    @Override
    public SynchronizationEmbedding getSync() {
        return Optional.ofNullable(sync).orElseGet(SynchronizationEmbedding::new);
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
