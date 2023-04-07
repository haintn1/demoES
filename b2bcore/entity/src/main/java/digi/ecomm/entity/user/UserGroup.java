package digi.ecomm.entity.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_groups", uniqueConstraints = {
        @UniqueConstraint(columnNames = {Principal.Fields.UID})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class UserGroup extends Principal {

    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.USER_DISCOUNT_GROUP)
    private UserDiscountGroup userDiscountGroup;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.USER_PRICE_GROUP)
    private UserPriceGroup userPriceGroup;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.USER_TAX_GROUP)
    private UserTaxGroup userTaxGroup;

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = Principal.Fields.GROUPS, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private Set<Principal> members = new HashSet<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
