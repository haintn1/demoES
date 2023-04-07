package digi.ecomm.entity.commercesearch.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.*;
import digi.ecomm.entity.commercesearch.advance.EsAdvancedSearchConfig;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsFacetSearchConfig.class})
public interface EsFacetSearchConfigProjection extends AbstractEntityProjection {

    String getName();

    String getDescription();

    EsSearchConfig getSearchConfig();

    EsIndexConfig getIndexConfig();

    EsServerConfig getServerConfig();

    List<EsIndex> getIndices();

    List<EsSynonymConfig> getSynonymConfigs();

    List<EsStopWord> getStopWords();

    EsAdvancedSearchConfig getAdvancedSearchConfig();
}
