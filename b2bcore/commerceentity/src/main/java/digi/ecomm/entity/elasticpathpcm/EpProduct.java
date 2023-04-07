package digi.ecomm.entity.elasticpathpcm;

import digi.ecomm.entity.pcm.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EpProduct extends Product implements EpProductSharedAttribute {

    private static final long serialVersionUID = 1L;

    @Embedded
    private EpProductSharedAttributeEmbedding commonAttributes;

    @Override
    public EpProductSharedAttributeEmbedding getSharedAttributes() {
        return commonAttributes;
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
