package digi.ecomm.entity.email;

import digi.ecomm.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "email_messages", indexes = {
        @Index(columnList = EmailMessage.Fields.SENT) //NOSONAR
})
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EmailMessage extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String subject;

    @Column
    private String body;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentDate;

    @Column
    private Boolean sent;

    @OneToMany(mappedBy = EmailAttachment.Fields.MESSAGE, fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<EmailAttachment> attachments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "email_message_2_to_address",
            joinColumns = @JoinColumn(name = "email_message_id"),
            inverseJoinColumns = @JoinColumn(name = "to_address_id")
    )
    private Set<EmailAddress> toAddresses = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "email_message_2_cc_address",
            joinColumns = @JoinColumn(name = "email_message_id"),
            inverseJoinColumns = @JoinColumn(name = "cc_address_id")
    )
    private Set<EmailAddress> ccAddresses = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "email_message_2_bcc_address",
            joinColumns = @JoinColumn(name = "email_message_id"),
            inverseJoinColumns = @JoinColumn(name = "bcc_address_id")
    )
    private Set<EmailAddress> bccAddresses = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.FROM_ADDRESS)
    private EmailAddress fromAddress;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
