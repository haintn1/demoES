package digi.ecomm.entity.store;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.user.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {PointOfService.Fields.NAME})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class PointOfService extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String name;

    @Column
    private String displayName;

    @Column
    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.ADDRESS)
    private Address address;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.OPENING_SCHEDULE)
    private OpeningSchedule openingSchedule;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "pos_2_warehouse",
            joinColumns = @JoinColumn(name = "pos_id"),
            inverseJoinColumns = @JoinColumn(name = "warehouse_id")
    )
    private Set<Warehouse> warehouses = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.BASE_STORE)
    private BaseStore baseStore;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
