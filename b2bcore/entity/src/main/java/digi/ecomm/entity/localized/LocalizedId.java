package digi.ecomm.entity.localized;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@FieldNameConstants
public class LocalizedId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long entityId;
    private String language;

    public LocalizedId() {
    }

    public LocalizedId(final String language) {
        this.language = language;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((language == null) ? 0 : language.hashCode());
        result = prime * result + ((entityId == null) ? 0 : entityId.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final LocalizedId other = (LocalizedId) obj;
        if (language == null) {
            if (other.language != null) {
                return false;
            }
        } else if (!language.equals(other.language)) {
            return false;
        }

        if (entityId == null) {
            if (other.entityId != null) {
                return false;
            }
        } else if (!entityId.equals(other.entityId)) {
            return false;
        }

        return true;
    }
}
