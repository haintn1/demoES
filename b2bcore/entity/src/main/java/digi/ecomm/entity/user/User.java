package digi.ecomm.entity.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import digi.ecomm.entity.c2l.Currency;
import digi.ecomm.entity.c2l.Language;
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
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public abstract class User extends Principal {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String encodedPassword;

    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Transient
    private String password;

    @Column
    private Boolean loginDisabled;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_2_refresh_token")
    private List<String> refreshTokens = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.SESSION_LANGUAGE)
    private Language sessionLanguage;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.SESSION_LANGUAGE)
    private Currency sessionCurrency;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PRICE_GROUP)
    private UserPriceGroup priceGroup;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.TAX_GROUP)
    private UserTaxGroup taxGroup;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.DISCOUNT_GROUP)
    private UserDiscountGroup discountGroup;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
