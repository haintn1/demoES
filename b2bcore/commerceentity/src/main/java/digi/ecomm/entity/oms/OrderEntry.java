package digi.ecomm.entity.oms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "order_entries", uniqueConstraints = {
        @UniqueConstraint(columnNames = {AbstractOrderEntry.Fields.ENTRY_NUMBER, AbstractOrderEntry.PRODUCT_ID})})
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class OrderEntry extends AbstractOrderEntry {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.ORDER)
    private Order order;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
