package digi.ecomm.entity.c2l;

import digi.ecomm.entity.localized.AbstractL10NAugmentation;
import digi.ecomm.entity.localized.LocalizableEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.*;
import java.util.function.Supplier;

@Entity
@Table(name = "countries", uniqueConstraints = {@UniqueConstraint(columnNames = {C2LItem.Fields.ISOCODE})}) //NOSONAR
@Getter
@Setter
@NoArgsConstructor
@FieldNameConstants
public class Country extends C2LItem implements LocalizableEntity<CountryL10NAugmentation> {

    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = CountryL10NAugmentation.Fields.COUNTRY, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKey(name = AbstractL10NAugmentation.LANGUAGE_KEY)
    private Map<String, CountryL10NAugmentation> translations = new HashMap<>();

    @OneToMany(mappedBy = Region.Fields.COUNTRY, fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private List<Region> regions = new ArrayList<>();

    @Override
    public Supplier<CountryL10NAugmentation> translationSupplier() {
        return CountryL10NAugmentation::new;
    }

    @Override
    public String getName() {
        return getLocalizedValue(CountryL10NAugmentation::getName);
    }

    @Override
    public String getName(final Locale locale) {
        return getLocalizedValue(locale, CountryL10NAugmentation::getName);
    }

    @Override
    public void setName(final String name) {
        setLocalizedValue(name, CountryL10NAugmentation::setName);
    }

    @Override
    public void setName(final Locale locale, final String name) {
        setLocalizedValue(locale, name, CountryL10NAugmentation::setName);
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
