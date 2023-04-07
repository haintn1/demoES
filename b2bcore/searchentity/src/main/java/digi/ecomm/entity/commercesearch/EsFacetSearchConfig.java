package digi.ecomm.entity.commercesearch;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.commercesearch.advance.EsAdvancedSearchConfig;
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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {EsFacetSearchConfig.Fields.NAME}) //NOSONAR
})
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class EsFacetSearchConfig extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String FACET_SEARCH_CONFIG_PATH = "facet-search-config";
    public static final String FACET_SEARCH_CONFIGS_PATH = "facet-search-configs";

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @OneToOne(mappedBy = EsSearchConfig.Fields.FACET_SEARCH_CONFIG, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.SEARCH_CONFIG)
    @RestResource(path = EsSearchConfig.SEARCH_CONFIG_PATH)
    private EsSearchConfig searchConfig;

    @OneToOne(mappedBy = EsIndexConfig.Fields.FACET_SEARCH_CONFIG, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.INDEX_CONFIG)
    @RestResource(path = EsIndexConfig.INDEX_CONFIG_PATH)
    private EsIndexConfig indexConfig;

    @OneToOne(mappedBy = EsServerConfig.Fields.FACET_SEARCH_CONFIG, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.SERVER_CONFIG)
    @RestResource(path = EsServerConfig.SERVER_CONFIG_PATH)
    private EsServerConfig serverConfig;

    @OneToMany(mappedBy = EsIndex.Fields.FACET_SEARCH_CONFIG, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    @RestResource(path = EsIndex.INDICES_PATH)
    private List<EsIndex> indices = new ArrayList<>();

    @OneToMany(mappedBy = EsSynonymConfig.Fields.FACET_SEARCH_CONFIG, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    @RestResource(path = EsSynonymConfig.SYNONYM_CONFIGS_PATH)
    private List<EsSynonymConfig> synonymConfigs = new ArrayList<>();

    @OneToMany(mappedBy = EsStopWord.Fields.FACET_SEARCH_CONFIG, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @Setter(AccessLevel.NONE)
    @RestResource(path = EsStopWord.STOP_WORDS_PATH)
    private List<EsStopWord> stopWords = new ArrayList<>();

    @OneToOne(mappedBy = EsAdvancedSearchConfig.Fields.FACET_SEARCH_CONFIG, fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
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
