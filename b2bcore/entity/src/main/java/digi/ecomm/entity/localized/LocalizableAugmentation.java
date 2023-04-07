package digi.ecomm.entity.localized;

import java.io.Serializable;

public interface LocalizableAugmentation<E extends LocalizableEntity> extends Serializable {

    /**
     * Set localized id.
     *
     * @param localizedId
     */
    void setLocalizedId(LocalizedId localizedId);

    /**
     * Set entity.
     *
     * @param entity
     */
    void setEntity(E entity);
}
