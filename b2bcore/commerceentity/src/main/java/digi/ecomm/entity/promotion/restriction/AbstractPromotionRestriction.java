package digi.ecomm.entity.promotion.restriction;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.promotion.AbstractPromotion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "promotion_restrictions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {AbstractPromotionRestriction.Fields.CODE})} //NOSONAR
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public abstract class AbstractPromotionRestriction extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PROMOTION)
    private AbstractPromotion promotion;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
