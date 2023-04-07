package digi.ecomm.entity.store;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.c2l.Currency;
import digi.ecomm.entity.c2l.Language;
import digi.ecomm.entity.store.converter.DistanceUnitConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "base_stores", uniqueConstraints = {
        @UniqueConstraint(columnNames = {BaseStore.Fields.UID})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class BaseStore extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String uid;

    @Column
    private String name;

    @Column
    private Boolean expressCheckoutEnabled;

    @Column(nullable = false)
    private String apiBaseUrl;

    @Column(nullable = false)
    private String clientId;

    @Column(nullable = false)
    private String clientSecret;

    /**
     * Name of payment provider used for credit card subscriptions.
     */
    @Column
    private String paymentProvider;

    @Column
    @Convert(converter = DistanceUnitConverter.class)
    private DistanceUnit distanceUnit;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.DEFAULT_LANGUAGE)
    private Language defaultLanguage;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.DEFAULT_CURRENCY)
    private Currency defaultCurrency;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.DEFAULT_DELIVERY_ORIGIN)
    private PointOfService defaultDeliveryOrigin;

    @OneToMany(mappedBy = PointOfService.Fields.BASE_STORE, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    private List<PointOfService> pointOfServices = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "basestore_2_currency",
            joinColumns = @JoinColumn(name = "basestore_id"),
            inverseJoinColumns = @JoinColumn(name = "currency_id")
    )
    private Set<Currency> currencies = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "basestore_2_language",
            joinColumns = @JoinColumn(name = "basestore_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> languages = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "basestore_2_warehouse",
            joinColumns = @JoinColumn(name = "basestore_id"),
            inverseJoinColumns = @JoinColumn(name = "warehouse_id")
    )
    private Set<Warehouse> warehouses = new HashSet<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
