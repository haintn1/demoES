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
@Table(name = "products_l10n")
@FieldNameConstants
public class ProductL10NAugmentation extends AbstractL10NAugmentation<Product> {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Column
    private String name;

    @Getter
    @Setter
    @Column
    private String description;

    @Getter
    @Setter
    @Column
    private String summary;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId(LocalizedId.Fields.ENTITY_ID)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PRODUCT)
    private Product product;

    @Override
    public void setEntity(final Product entity) {
        this.product = entity;
    }
}
