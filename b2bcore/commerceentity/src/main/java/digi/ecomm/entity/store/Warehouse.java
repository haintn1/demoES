package digi.ecomm.entity.store;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.pcm.Stock;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "warehouses", uniqueConstraints = {
        @UniqueConstraint(columnNames = {Warehouse.Fields.CODE})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Warehouse extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String code;

    @Column
    private String name;

    @Column(nullable = false)
    private Boolean defaultWareHouse = Boolean.FALSE;

    @OneToMany(mappedBy = Stock.Fields.WAREHOUSE, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    private Set<Stock> stocks = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = PointOfService.Fields.WAREHOUSES, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private Set<PointOfService> pointOfServices = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = BaseStore.Fields.WAREHOUSES, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private Set<BaseStore> baseStores = new HashSet<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
