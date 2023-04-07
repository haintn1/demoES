package digi.ecomm.entity.catalog;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.localized.AbstractL10NAugmentation;
import digi.ecomm.entity.localized.LocalizableEntity;
import digi.ecomm.entity.pcm.CatalogVersion;
import digi.ecomm.entity.sync.SynchronizableEntity;
import digi.ecomm.entity.sync.SynchronizationEmbedding;
import digi.ecomm.entity.user.Principal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.*;
import java.util.function.Supplier;

@Entity
@Table(name = "catalogs", uniqueConstraints = {
        @UniqueConstraint(columnNames = {Catalog.Fields.CODE, Catalog.Fields.VERSION})}, //NOSONAR
        indexes = {
                @Index(columnList = SynchronizationEmbedding.Fields.EXTERNAL_ID, unique = true)}
)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Catalog extends AbstractEntity implements SynchronizableEntity, LocalizableEntity<Catalog10NAugmentation> {

    private static final long serialVersionUID = 1L;

    @Embedded
    private SynchronizationEmbedding sync = new SynchronizationEmbedding();

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CatalogVersion version;

    @Column
    private Boolean defaultCatalog;

    @Column
    private Boolean active;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = Catalog10NAugmentation.Fields.CATALOG, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKey(name = AbstractL10NAugmentation.LANGUAGE_KEY)
    private Map<String, Catalog10NAugmentation> translations = new HashMap<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "catalog_2_principal",
            joinColumns = @JoinColumn(name = "catalog_id"),
            inverseJoinColumns = @JoinColumn(name = "principal_id")
    )
    private Set<Principal> allowedPrincipals = new HashSet<>();

    @Override
    public SynchronizationEmbedding getSync() {
        return sync;
    }

    @Override
    public Supplier<Catalog10NAugmentation> translationSupplier() {
        return Catalog10NAugmentation::new;
    }

    /**
     * Get name.
     *
     * @return
     */
    public String getName() {
        return getLocalizedValue(Catalog10NAugmentation::getName);
    }

    /**
     * Get name.
     *
     * @param locale
     * @return
     */
    public String getName(final Locale locale) {
        return getLocalizedValue(locale, Catalog10NAugmentation::getName);
    }

    /**
     * Set name.
     *
     * @param name
     * @return
     */
    public void setName(final String name) {
        setLocalizedValue(name, Catalog10NAugmentation::setName);
    }

    /**
     * Set name.
     *
     * @param locale
     * @param name
     * @return
     */
    public void setName(final Locale locale, final String name) {
        setLocalizedValue(locale, name, Catalog10NAugmentation::setName);
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
