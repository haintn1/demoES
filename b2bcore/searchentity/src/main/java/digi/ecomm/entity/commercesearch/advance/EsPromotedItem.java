package digi.ecomm.entity.commercesearch.advance;

import digi.ecomm.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EsPromotedItem extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String PROMOTED_ITEMS_PATH = "promoted-items";

    @Column(nullable = false)
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.ADVANCED_SEARCH_CONFIG)
    @RestResource(path = EsAdvancedSearchConfig.ADVANCED_SEARCH_CONFIG_PATH)
    private EsAdvancedSearchConfig advancedSearchConfig;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
