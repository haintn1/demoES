package digi.ecomm.entity.pcm;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.localized.AbstractL10NAugmentation;
import digi.ecomm.entity.localized.LocalizableEntity;
import digi.ecomm.entity.sync.SynchronizableEntity;
import digi.ecomm.entity.sync.SynchronizationEmbedding;
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
@Table(name = "attribute_units", uniqueConstraints = {
        @UniqueConstraint(columnNames = {AttributeUnit.Fields.CODE})}, //NOSONAR
        indexes = {
                @Index(columnList = SynchronizationEmbedding.Fields.EXTERNAL_ID, unique = true)}
)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class AttributeUnit extends AbstractEntity implements SynchronizableEntity, LocalizableEntity<AttributeUnit10NAugmentation> {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String code;

    @Column
    private String symbol;

    @Embedded
    private SynchronizationEmbedding sync = new SynchronizationEmbedding();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = AttributeUnit10NAugmentation.Fields.ATTRIBUTE_UNIT, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKey(name = AbstractL10NAugmentation.LANGUAGE_KEY)
    private Map<String, AttributeUnit10NAugmentation> translations = new HashMap<>();

    @Override
    public SynchronizationEmbedding getSync() {
        return sync;
    }

    @Override
    public Supplier<AttributeUnit10NAugmentation> translationSupplier() {
        return AttributeUnit10NAugmentation::new;
    }

    /**
     * Get name.
     *
     * @return
     */
    public String getName() {
        return getLocalizedValue(AttributeUnit10NAugmentation::getName);
    }

    /**
     * Get name.
     *
     * @param locale
     * @return
     */
    public String getName(final Locale locale) {
        return getLocalizedValue(locale, AttributeUnit10NAugmentation::getName);
    }

    /**
     * Set name.
     *
     * @param name
     * @return
     */
    public void setName(final String name) {
        setLocalizedValue(name, AttributeUnit10NAugmentation::setName);
    }

    /**
     * Set name.
     *
     * @param locale
     * @param name
     * @return
     */
    public void setName(final Locale locale, final String name) {
        setLocalizedValue(locale, name, AttributeUnit10NAugmentation::setName);
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
