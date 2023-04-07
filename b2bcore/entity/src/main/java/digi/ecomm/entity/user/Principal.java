package digi.ecomm.entity.user;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.media.Media;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public abstract class Principal extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String uid;

    @Column
    private String name;

    @Column
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PROFILE_PICTURE)
    private Media profilePicture;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.SHIPPING_ADDRESS)
    private Address shippingAddress;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.BILLING_ADDRESS)
    private Address billingAddress;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @Setter(AccessLevel.NONE)
    private List<Address> addresses = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "principal_2_group",
            joinColumns = @JoinColumn(name = "principal_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<UserGroup> groups = new HashSet<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
