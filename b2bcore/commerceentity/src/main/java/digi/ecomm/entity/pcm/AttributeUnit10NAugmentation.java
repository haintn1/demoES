package digi.ecomm.entity.pcm;

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
@Table(name = "attribute_units_l10n")
@FieldNameConstants
public class AttributeUnit10NAugmentation extends AbstractL10NAugmentation<AttributeUnit> {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId(LocalizedId.Fields.ENTITY_ID)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.ATTRIBUTE_UNIT)
    private AttributeUnit attributeUnit;

    @Override
    public void setEntity(final AttributeUnit entity) {
        this.attributeUnit = entity;
    }
}
