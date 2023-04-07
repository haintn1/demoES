package digi.ecomm.entity;

import digi.ecomm.entity.localized.AbstractL10NAugmentation;
import digi.ecomm.entity.localized.LocalizedId;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "enums_l10n")
@FieldNameConstants
public class EnumEntity10NAugmentation extends AbstractL10NAugmentation<EnumEntity> {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId(LocalizedId.Fields.ENTITY_ID)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.ENUM_ENTITY)
    private EnumEntity enumEntity;

    @Override
    public void setEntity(final EnumEntity entity) {
        this.enumEntity = entity;
    }
}
