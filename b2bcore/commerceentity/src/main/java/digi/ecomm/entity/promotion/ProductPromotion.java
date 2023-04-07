package digi.ecomm.entity.promotion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public abstract class ProductPromotion extends AbstractPromotion {

    private static final long serialVersionUID = 1L;

    @Column
    private String messageFired;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
