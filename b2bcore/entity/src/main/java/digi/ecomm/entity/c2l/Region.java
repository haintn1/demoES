package digi.ecomm.entity.c2l;

import digi.ecomm.entity.localized.AbstractL10NAugmentation;
import digi.ecomm.entity.localized.LocalizableEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

@Entity
@Table(name = "regions", uniqueConstraints = {@UniqueConstraint(columnNames = {C2LItem.Fields.ISOCODE})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Region extends C2LItem implements LocalizableEntity<RegionL10NAugmentation> {

    private static final long serialVersionUID = 1L;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = RegionL10NAugmentation.Fields.REGION, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKey(name = AbstractL10NAugmentation.LANGUAGE_KEY)
    private Map<String, RegionL10NAugmentation> translations = new HashMap<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.COUNTRY)
    private Country country;

    @Override
    public Supplier<RegionL10NAugmentation> translationSupplier() {
        return RegionL10NAugmentation::new;
    }

    @Override
    public String getName() {
        return getLocalizedValue(RegionL10NAugmentation::getName);
    }

    @Override
    public String getName(final Locale locale) {
        return getLocalizedValue(locale, RegionL10NAugmentation::getName);
    }

    @Override
    public void setName(final String name) {
        setLocalizedValue(name, RegionL10NAugmentation::setName);
    }

    @Override
    public void setName(final Locale locale, final String name) {
        setLocalizedValue(locale, name, RegionL10NAugmentation::setName);
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
