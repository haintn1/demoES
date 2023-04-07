package digi.ecomm.entity.commercesearch;

import digi.ecomm.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {EsIndexConfig.Fields.NAME})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EsIndexConfig extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String INDEX_CONFIG_PATH = "index-config";

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int batchSize = 1000;

    @Column(nullable = false)
    private int batchBytes = 5 * 1024;

    @Column
    private int numConcurrentRequests = 0;

    @Column
    private int flushIntervalSeconds = 10;

    @Column
    private int maxRetries = 3;

    @Column
    private int retryDelaySeconds = 1;

    @Column
    private boolean dynamicMappingAllowed = true;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.FACET_SEARCH_CONFIG)
    @RestResource(path = EsFacetSearchConfig.FACET_SEARCH_CONFIG_PATH)
    private EsFacetSearchConfig facetSearchConfig;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
