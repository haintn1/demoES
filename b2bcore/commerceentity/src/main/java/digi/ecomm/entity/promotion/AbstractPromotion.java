package digi.ecomm.entity.promotion;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.promotion.restriction.AbstractPromotionRestriction;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "promotions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {AbstractPromotion.Fields.CODE})}, //NOSONAR
        indexes = {
                @Index(columnList = AbstractPromotion.Fields.ENABLED) //NOSONAR
        }
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class AbstractPromotion extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String code;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column
    private Boolean enabled;

    /**
     * The higher the value the higher the priority.
     */
    @Column
    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.PROMOTION_GROUP)
    private PromotionGroup promotionGroup;

    /**
     * Collection of restrictions that are applied to this promotion.
     */
    @OneToMany(mappedBy = AbstractPromotionRestriction.Fields.PROMOTION, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    private List<AbstractPromotionRestriction> restrictions = new ArrayList<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
