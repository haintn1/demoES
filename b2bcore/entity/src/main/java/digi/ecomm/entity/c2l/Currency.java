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
@Table(name = "currencies", uniqueConstraints = {@UniqueConstraint(columnNames = {C2LItem.Fields.ISOCODE})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Currency extends C2LItem implements LocalizableEntity<CurrencyL10NAugmentation> {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Boolean base;

    @Column(nullable = false)
    private Double conversion = 1.0;

    @Column(nullable = false)
    private Integer digits = 2;

    @Column(nullable = false)
    private String symbol;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = CurrencyL10NAugmentation.Fields.CURRENCY, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKey(name = AbstractL10NAugmentation.LANGUAGE_KEY)
    private Map<String, CurrencyL10NAugmentation> translations = new HashMap<>();

    @Override
    public Supplier<CurrencyL10NAugmentation> translationSupplier() {
        return CurrencyL10NAugmentation::new;
    }

    @Override
    public String getName() {
        return getLocalizedValue(CurrencyL10NAugmentation::getName);
    }

    @Override
    public String getName(final Locale locale) {
        return getLocalizedValue(locale, CurrencyL10NAugmentation::getName);
    }

    @Override
    public void setName(final String name) {
        setLocalizedValue(name, CurrencyL10NAugmentation::setName);
    }

    @Override
    public void setName(final Locale locale, final String name) {
        setLocalizedValue(locale, name, CurrencyL10NAugmentation::setName);
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
