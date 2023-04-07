package digi.ecomm.entity.localized;

import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@FieldNameConstants
public abstract class AbstractL10NAugmentation<E extends LocalizableEntity> implements LocalizableAugmentation<E> {

    private static final long serialVersionUID = 1L;

    public static final String LANGUAGE_KEY = "localizedId.language";

    /**
     * MUST NOT have getter.
     */
    @EmbeddedId
    private LocalizedId localizedId;

    public void setLocalizedId(final LocalizedId localizedId) {
        this.localizedId = localizedId;
    }
}
