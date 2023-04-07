package digi.ecomm.entity.promotion;

import digi.ecomm.entity.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "promotion_groups", uniqueConstraints = {
        @UniqueConstraint(columnNames = {PromotionGroup.Fields.IDENTIFIER})} //NOSONAR
)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class PromotionGroup extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String identifier;

    @OneToMany(mappedBy = AbstractPromotion.Fields.PROMOTION_GROUP, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE}, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<AbstractPromotion> promotions = new ArrayList<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
