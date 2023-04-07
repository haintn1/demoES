package digi.ecomm.entity.pcm;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.catalog.Catalog;
import digi.ecomm.entity.localized.AbstractL10NAugmentation;
import digi.ecomm.entity.localized.LocalizableEntity;
import digi.ecomm.entity.media.Media;
import digi.ecomm.entity.sync.SynchronizableEntity;
import digi.ecomm.entity.sync.SynchronizationEmbedding;
import digi.ecomm.entity.user.Principal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.*;
import java.util.function.Supplier;

@Entity
@Table(name = "categories", uniqueConstraints = {
        @UniqueConstraint(columnNames = {Category.Fields.CODE, Category.CATALOG_ID})}, //NOSONAR
        indexes = {
                @Index(columnList = SynchronizationEmbedding.Fields.EXTERNAL_ID, unique = true)}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Category extends AbstractEntity implements SynchronizableEntity, LocalizableEntity<Category10NAugmentation> {

    private static final long serialVersionUID = 1L;

    static final String CATALOG_ID = "catalog_id";

    @Embedded
    private SynchronizationEmbedding sync = new SynchronizationEmbedding();

    @Column(nullable = false)
    private String code;

    @Column
    private String name;

    @Column
    private String description;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = Category10NAugmentation.Fields.CATEGORY, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKey(name = AbstractL10NAugmentation.LANGUAGE_KEY)
    private Map<String, Category10NAugmentation> translations = new HashMap<>();

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.MAIN_IMAGE)
    private Media mainImage;

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = Product.Fields.CATEGORIES, fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = Fields.SUPER_CATEGORIES, fetch = FetchType.LAZY)
    private Set<Category> subCategories = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "category_2_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "super_category_id")
    )
    private Set<Category> superCategories = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CATALOG)
    @JoinColumn(name = Category.CATALOG_ID, referencedColumnName = AbstractEntity.Fields.ID)
    private Catalog catalog;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "category_2_principal",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "principal_id")
    )
    private Set<Principal> allowedPrincipals = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "category_2_template",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "template_id")
    )
    private Set<AttributeTemplate> attributeTemplates = new HashSet<>();

    @Override
    public SynchronizationEmbedding getSync() {
        return sync;
    }

    @Override
    public Supplier<Category10NAugmentation> translationSupplier() {
        return Category10NAugmentation::new;
    }

    /**
     * Get name.
     *
     * @return
     */
    public String getName() {
        return getLocalizedValue(Category10NAugmentation::getName);
    }

    /**
     * Get name.
     *
     * @param locale
     * @return
     */
    public String getName(final Locale locale) {
        return getLocalizedValue(locale, Category10NAugmentation::getName);
    }

    /**
     * Set name.
     *
     * @param name
     * @return
     */
    public void setName(final String name) {
        setLocalizedValue(name, Category10NAugmentation::setName);
    }

    /**
     * Set name.
     *
     * @param locale
     * @param name
     * @return
     */
    public void setName(final Locale locale, final String name) {
        setLocalizedValue(locale, name, Category10NAugmentation::setName);
    }

    /**
     * Get description.
     *
     * @return
     */
    public String getDescription() {
        return getLocalizedValue(Category10NAugmentation::getDescription);
    }

    /**
     * Get description.
     *
     * @param locale
     * @return
     */
    public String getDescription(final Locale locale) {
        return getLocalizedValue(locale, Category10NAugmentation::getDescription);
    }

    /**
     * Set description.
     *
     * @param description
     * @return
     */
    public void setDescription(final String description) {
        setLocalizedValue(description, Category10NAugmentation::setDescription);
    }

    /**
     * Set description.
     *
     * @param locale
     * @param description
     * @return
     */
    public void setDescription(final Locale locale, final String description) {
        setLocalizedValue(locale, description, Category10NAugmentation::setDescription);
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
