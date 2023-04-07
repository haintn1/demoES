package digi.ecomm.entity.commercesearch;

import digi.ecomm.entity.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {EsServerConfig.Fields.NAME})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EsServerConfig extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String SERVER_CONFIG_PATH = "server-config";
    public static final String SERVER_CONFIGS_PATH = "server-configs";

    @Column(nullable = false)
    private String name;

    /**
     * Sets the timeout in milliseconds until a connection is established.
     */
    @Column
    private Integer connectionTimeout = Integer.valueOf(8000);

    /**
     * Sets the default socket timeout in milliseconds which is the timeout for waiting for data.
     */
    @Column
    private Integer socketTimeout = Integer.valueOf(5000);

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Integer numShards = Integer.valueOf(1);

    @Column
    private Integer replicationFactor = Integer.valueOf(1);

    @OneToMany(mappedBy = EsServer.Fields.SERVER_CONFIG, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    @RestResource(path = EsServer.SERVERS_PATH)
    private List<EsServer> servers = new ArrayList<>();

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
