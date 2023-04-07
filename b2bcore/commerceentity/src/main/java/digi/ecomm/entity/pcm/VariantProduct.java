package digi.ecomm.entity.pcm;

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
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class VariantProduct extends Product {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.BASE_PRODUCT)
    private Product baseProduct;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "variant_product_2_variation_option",
            joinColumns = @JoinColumn(name = "variant_product_id"),
            inverseJoinColumns = @JoinColumn(name = "variation_option_id")
    )
    private Set<VariationOption> options = new HashSet<>();

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}