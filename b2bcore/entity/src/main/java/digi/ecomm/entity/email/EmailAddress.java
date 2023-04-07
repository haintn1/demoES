package digi.ecomm.entity.email;

import digi.ecomm.entity.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "email_addresses", uniqueConstraints = {
        @UniqueConstraint(columnNames = {EmailAddress.Fields.ADDRESS, EmailAddress.Fields.DISPLAY_NAME}) //NOSONAR
})
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EmailAddress extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String displayName;

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = EmailMessage.Fields.TO_ADDRESSES, fetch = FetchType.LAZY)
    private Set<EmailMessage> toMessages = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = EmailMessage.Fields.CC_ADDRESSES, fetch = FetchType.LAZY)
    private Set<EmailMessage> ccMessages = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = EmailMessage.Fields.BCC_ADDRESSES, fetch = FetchType.LAZY)
    private Set<EmailMessage> bccMessages = new HashSet<>();

    @OneToMany(mappedBy = EmailMessage.Fields.FROM_ADDRESS, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmailMessage> messagesSent = new ArrayList<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
