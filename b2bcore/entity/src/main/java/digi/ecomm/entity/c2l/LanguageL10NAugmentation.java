package digi.ecomm.entity.c2l;

import digi.ecomm.entity.localized.LocalizedId;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "languages_l10n")
@FieldNameConstants
public class LanguageL10NAugmentation extends C2LL10NAugmentation<Language> {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId(LocalizedId.Fields.ENTITY_ID)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.LANGUAGE)
    private Language language;

    @Override
    public void setEntity(final Language entity) {
        this.language = entity;
    }
}
