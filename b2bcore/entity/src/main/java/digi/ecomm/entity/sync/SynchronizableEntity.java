package digi.ecomm.entity.sync;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public interface SynchronizableEntity {

    /**
     * Get synchronization.
     *
     * @return
     */
    @JsonIgnore
    SynchronizationEmbedding getSync();

    /**
     * Get id.
     *
     * @return
     */
    Long getId();

    /**
     * Get external id.
     *
     * @return
     */
    default String getExternalId() {
        return getSync() != null ? getSync().getExternalId() : null;
    }

    /**
     * Set external id.
     *
     * @return
     */
    default void setExternalId(final String externalId) {
        Optional.ofNullable(getSync()).ifPresent(sync -> sync.setExternalId(externalId));
    }

    /**
     * Check if entity is tracked by third party.
     *
     * @return
     */
    @JsonIgnore
    default boolean isTracked() {
        return StringUtils.isNoneBlank(getExternalId());
    }

    /**
     * Check if entity should not be sync.
     *
     * @return
     */
    default boolean isStopSync() {
        return getSync() != null && Boolean.TRUE.equals(getSync().getStopSync());
    }
}
