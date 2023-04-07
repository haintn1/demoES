package digi.ecomm.entity.c2l;

import digi.ecomm.entity.localized.AbstractL10NAugmentation;
import digi.ecomm.entity.localized.LocalizableEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

@Entity
@Table(name = "languages", uniqueConstraints = {@UniqueConstraint(columnNames = {C2LItem.Fields.ISOCODE})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Language extends C2LItem implements LocalizableEntity<LanguageL10NAugmentation> {

    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = LanguageL10NAugmentation.Fields.LANGUAGE, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKey(name = AbstractL10NAugmentation.LANGUAGE_KEY)
    private Map<String, LanguageL10NAugmentation> translations = new HashMap<>();

    @Override
    public Supplier<LanguageL10NAugmentation> translationSupplier() {
        return LanguageL10NAugmentation::new;
    }

    @Override
    public String getName() {
        return getLocalizedValue(LanguageL10NAugmentation::getName);
    }

    @Override
    public String getName(final Locale locale) {
        return getLocalizedValue(locale, LanguageL10NAugmentation::getName);
    }

    @Override
    public void setName(final String name) {
        setLocalizedValue(name, LanguageL10NAugmentation::setName);
    }

    @Override
    public void setName(final Locale locale, final String name) {
        setLocalizedValue(locale, name, LanguageL10NAugmentation::setName);
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
