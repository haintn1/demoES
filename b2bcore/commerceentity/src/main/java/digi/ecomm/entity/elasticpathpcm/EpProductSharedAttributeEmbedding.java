package digi.ecomm.entity.elasticpathpcm;

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
public class EpProductSharedAttributeEmbedding implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    private String commodityType;
}
