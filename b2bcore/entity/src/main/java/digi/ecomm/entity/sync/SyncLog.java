package digi.ecomm.entity.sync;

import digi.ecomm.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity
@Table(name = "sync_logs")
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class SyncLog extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String typeClass;

    @Column(nullable = false)
    private Long entityId;

    @Column
    private String externalId;

    /**
     * Used when {@link ActionType#LINKED} or{@link ActionType#UNLINKED}.
     */
    @Column
    private String linkedClass;

    /**
     * Used when {@link ActionType#LINKED} or{@link ActionType#UNLINKED}.
     */
    @Column
    private Long linkedId;

    /**
     * Used when {@link ActionType#LINKED} or{@link ActionType#UNLINKED}.
     */
    @Column
    private String linkedExternalId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    @Column
    private int retries;

    @Column
    private String message;

    @Column
    private Boolean archived;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
