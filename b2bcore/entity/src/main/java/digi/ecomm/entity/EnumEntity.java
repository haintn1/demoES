package digi.ecomm.entity;

import digi.ecomm.entity.localized.AbstractL10NAugmentation;
import digi.ecomm.entity.localized.LocalizableEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Entity
@Table(name = "enums", uniqueConstraints = {
        @UniqueConstraint(columnNames = {EnumEntity.Fields.CODE}) //NOSONAR
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public abstract class EnumEntity extends AbstractEntity implements LocalizableEntity<EnumEntity10NAugmentation> {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String code;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = EnumEntity10NAugmentation.Fields.ENUM_ENTITY, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKey(name = AbstractL10NAugmentation.LANGUAGE_KEY)
    private Map<String, EnumEntity10NAugmentation> translations = new HashMap<>();

    @Override
    public Supplier<EnumEntity10NAugmentation> translationSupplier() {
        return EnumEntity10NAugmentation::new;
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
