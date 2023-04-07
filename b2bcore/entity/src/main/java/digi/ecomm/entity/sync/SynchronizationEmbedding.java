package digi.ecomm.entity.sync;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class SynchronizationEmbedding implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The unique id of entity in 3rd party system.
     */
    @Column(updatable = false)
    private String externalId;

    @Column
    private Boolean stopSync;
}
