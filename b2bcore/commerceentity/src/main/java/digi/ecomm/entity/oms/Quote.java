package digi.ecomm.entity.oms;

import digi.ecomm.entity.organization.Customer;
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
import java.util.List;

@Entity
@Table(name = "quotes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {AbstractOrder.Fields.CODE, Quote.Fields.VERSION}) //NOSONAR
})
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Quote extends AbstractOrder {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private int version;

    @Enumerated(EnumType.STRING)
    private QuoteState state;

    @OneToMany(mappedBy = QuoteEntry.Fields.ORDER, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    private List<QuoteEntry> entries = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CUSTOMER)
    private Customer customer;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
