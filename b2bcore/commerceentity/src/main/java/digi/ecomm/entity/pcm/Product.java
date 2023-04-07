package digi.ecomm.entity.pcm;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.catalog.Catalog;
import digi.ecomm.entity.localized.AbstractL10NAugmentation;
import digi.ecomm.entity.localized.LocalizableEntity;
import digi.ecomm.entity.media.Media;
import digi.ecomm.entity.sync.SynchronizableEntity;
import digi.ecomm.entity.sync.SynchronizationEmbedding;
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
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(columnNames = {Product.Fields.CODE, Product.CATALOG_ID})}, //NOSONAR
        indexes = {
                @Index(columnList = SynchronizationEmbedding.Fields.EXTERNAL_ID, unique = true)} //NOSONAR
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Product extends AbstractEntity implements SynchronizableEntity, LocalizableEntity<ProductL10NAugmentation> {

    private static final long serialVersionUID = 1L;

    static final String CATALOG_ID = "catalog_id";

    @Embedded
    private SynchronizationEmbedding sync = new SynchronizationEmbedding();

    @Column(nullable = false)
    private String code;

    /**
     * Identify if the product's content is final and ready for buying or not.
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.DRAFT;

    @Column
    private Integer maxOrderQuantity;

    @Column
    private Integer minOrderQuantity;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date offlineDate;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date onlineDate;

    @Column
    private Boolean soldIndividually;

    @Column
    private Integer numberOfReviews;

    @Column
    private Integer averageRating;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = ProductL10NAugmentation.Fields.PRODUCT, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapKey(name = AbstractL10NAugmentation.LANGUAGE_KEY)
    private Map<String, ProductL10NAugmentation> translations = new HashMap<>();

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.MAIN_IMAGE)
    private Media mainImage;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "product_2_variation",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "variation_id")
    )
    private Set<Variation> variations = new HashSet<>();

    @OneToMany(mappedBy = VariantProduct.Fields.BASE_PRODUCT, fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private List<VariantProduct> variantProducts = new ArrayList<>();

    @OneToMany(mappedBy = ProductFeature.Fields.PRODUCT, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductFeature> features = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "product_2_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = PriceRow.Fields.PRODUCT, fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            orphanRemoval = true)
    private List<PriceRow> priceRows = new ArrayList<>();

    @OneToMany(mappedBy = TaxRow.Fields.PRODUCT, fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            orphanRemoval = true)
    private List<TaxRow> taxRows = new ArrayList<>();

    @OneToMany(mappedBy = DiscountRow.Fields.PRODUCT, fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            orphanRemoval = true)
    private List<DiscountRow> discountRows = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CATALOG)
    @JoinColumn(name = CATALOG_ID, referencedColumnName = AbstractEntity.Fields.ID)
    private Catalog catalog;

    @Override
    public SynchronizationEmbedding getSync() {
        return sync;
    }

    @Override
    public Supplier<ProductL10NAugmentation> translationSupplier() {
        return ProductL10NAugmentation::new;
    }

    /**
     * Get name.
     *
     * @return
     */
    public String getName() {
        return getLocalizedValue(ProductL10NAugmentation::getName);
    }

    /**
     * Get name.
     *
     * @param locale
     * @return
     */
    public String getName(final Locale locale) {
        return getLocalizedValue(locale, ProductL10NAugmentation::getName);
    }

    /**
     * Set name.
     *
     * @param name
     * @return
     */
    public void setName(final String name) {
        setLocalizedValue(name, ProductL10NAugmentation::setName);
    }

    /**
     * Set name.
     *
     * @param locale
     * @param name
     * @return
     */
    public void setName(final Locale locale, final String name) {
        setLocalizedValue(locale, name, ProductL10NAugmentation::setName);
    }

    /**
     * Get description.
     *
     * @return
     */
    public String getDescription() {
        return getLocalizedValue(ProductL10NAugmentation::getDescription);
    }

    /**
     * Get description.
     *
     * @param locale
     * @return
     */
    public String getDescription(final Locale locale) {
        return getLocalizedValue(locale, ProductL10NAugmentation::getDescription);
    }

    /**
     * Set description.
     *
     * @param description
     * @return
     */
    public void setDescription(final String description) {
        setLocalizedValue(description, ProductL10NAugmentation::setDescription);
    }

    /**
     * Set description.
     *
     * @param locale
     * @param description
     * @return
     */
    public void setDescription(final Locale locale, final String description) {
        setLocalizedValue(locale, description, ProductL10NAugmentation::setDescription);
    }

    /**
     * Get summary.
     *
     * @return
     */
    public String getSummary() {
        return getLocalizedValue(ProductL10NAugmentation::getSummary);
    }

    /**
     * Get summary.
     *
     * @param locale
     * @return
     */
    public String getSummary(final Locale locale) {
        return getLocalizedValue(locale, ProductL10NAugmentation::getSummary);
    }

    /**
     * Set summary.
     *
     * @param summary
     * @return
     */
    public void setSummary(final String summary) {
        setLocalizedValue(summary, ProductL10NAugmentation::setSummary);
    }

    /**
     * Set summary.
     *
     * @param locale
     * @param summary
     * @return
     */
    public void setSummary(final Locale locale, final String summary) {
        setLocalizedValue(locale, summary, ProductL10NAugmentation::setSummary);
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
