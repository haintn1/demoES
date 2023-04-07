package digi.ecomm.entity.user;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.c2l.Country;
import digi.ecomm.entity.c2l.Region;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Address extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column
    private Boolean duplicate;

    @Column
    private String company;

    @Column
    private String building;

    @Column
    private String email;

    @Column
    private String fax;

    @Column
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private String phone1;

    @Column
    private String phone2;

    @Column
    private String pobox;

    @Column
    private String postalcode;

    @Column
    private String streetNumber;

    @Column
    private String streetName;

    @Column
    private String town;

    @Column
    private String district;

    @Column
    private Boolean shippingAddress;

    @Column
    private Boolean billingAddress;

    @Column
    private Boolean contactAddress;

    @Column
    private Boolean visibleInAddressBook = Boolean.TRUE;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.REGION)
    private Region region;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.COUNTRY)
    private Country country;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.TITLE)
    private Title title;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.ORIGINAL)
    private Address original;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
