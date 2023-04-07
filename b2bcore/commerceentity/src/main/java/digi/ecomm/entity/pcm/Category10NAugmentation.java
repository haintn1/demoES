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
@Table(name = "categories_l10n")
@FieldNameConstants
public class Category10NAugmentation extends AbstractL10NAugmentation<Category> {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Column
    private String name;

    @Getter
    @Setter
    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId(LocalizedId.Fields.ENTITY_ID)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CATEGORY)
    private Category category;

    @Override
    public void setEntity(final Category entity) {
        this.category = entity;
    }
}
