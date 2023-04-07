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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = EsServer.Fields.NAME) //NOSONAR
})
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EsServer extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String SERVERS_PATH = "servers";

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String scheme;

    @Column(nullable = false)
    private String hostName;

    @Column(nullable = false)
    private int port;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.SERVER_CONFIG)
    @RestResource(path = EsServerConfig.SERVER_CONFIG_PATH)
    private EsServerConfig serverConfig;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
