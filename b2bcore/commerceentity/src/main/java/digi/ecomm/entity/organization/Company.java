package digi.ecomm.entity.organization;

import digi.ecomm.entity.user.Address;
import digi.ecomm.entity.user.User;
import digi.ecomm.entity.user.UserGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public abstract class Company extends UserGroup {

    private static final long serialVersionUID = 1L;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CONTACT_ADDRESS)
    private Address contactAddress;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CONTACT)
    private User contact;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
