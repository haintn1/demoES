package digi.ecomm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.util.ProxyUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@NoArgsConstructor
@FieldNameConstants
public abstract class AbstractEntity implements Persistable<Long>, Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    private static final String B2B_SEQUENCE = "b2b_sequence";

    // Recommended by Hibernate to get benefit from automatic batch insert
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = B2B_SEQUENCE)
    @SequenceGenerator(name = B2B_SEQUENCE, allocationSize = 100)
    private Long id;

    @Column
    private String createdBy;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;

    @Column
    private String lastModifiedBy;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastModifiedDate;

    @Version
    private short persistentVersion;

    @Override
    public Long getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return getId() == null;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }

    @Override
    public boolean equals(final Object obj) { //NOSONAR
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        }

        final AbstractEntity that = (AbstractEntity) obj;

        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public String toString() {
        return isNew()
                ? String.format("%s (<unsaved>)", this.getClass().getSimpleName())
                : String.format("%s (%s)", this.getClass().getSimpleName(), this.getId().toString());
    }
}
