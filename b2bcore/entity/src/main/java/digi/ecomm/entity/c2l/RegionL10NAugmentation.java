package digi.ecomm.entity.c2l;

import digi.ecomm.entity.localized.LocalizedId;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "regions_l10n")
@FieldNameConstants
public class RegionL10NAugmentation extends C2LL10NAugmentation<Region> {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId(LocalizedId.Fields.ENTITY_ID)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.REGION)
    private Region region;

    @Override
    public void setEntity(final Region entity) {
        this.region = entity;
    }
}
