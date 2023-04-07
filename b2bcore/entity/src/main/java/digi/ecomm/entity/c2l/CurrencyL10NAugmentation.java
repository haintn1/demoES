package digi.ecomm.entity.c2l;

import digi.ecomm.entity.localized.LocalizedId;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "currencies_l10n")
@FieldNameConstants
public class CurrencyL10NAugmentation extends C2LL10NAugmentation<Currency> {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId(LocalizedId.Fields.ENTITY_ID)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CURRENCY)
    private Currency currency;

    @Override
    public void setEntity(final Currency entity) {
        this.currency = entity;
    }
}
