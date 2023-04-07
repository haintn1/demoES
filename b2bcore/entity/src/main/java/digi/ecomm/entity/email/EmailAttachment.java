package digi.ecomm.entity.email;

import digi.ecomm.entity.media.Media;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EmailAttachment extends Media {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.MESSAGE)
    private EmailMessage message;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
