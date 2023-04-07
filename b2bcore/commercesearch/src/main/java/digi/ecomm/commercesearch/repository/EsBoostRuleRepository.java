package digi.ecomm.commercesearch.repository;

import digi.ecomm.entity.commercesearch.advance.EsAdvancedSearchConfig;
import digi.ecomm.entity.commercesearch.advance.EsBoostRule;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static digi.ecomm.platformservice.persistent.repository.BaseRepository.REST_COLLECTION_RESOURCE_REL;


@Repository("boostRuleRepository")
@RepositoryRestResource(path = EsBoostRuleRepository.PATH, collectionResourceRel = REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EsBoostRuleRepository.ITEM_RESOURCE_REL)
public interface EsBoostRuleRepository extends BaseRepository<EsBoostRule, Long> {

    String PATH = "boost-rules";
    String ITEM_RESOURCE_REL = "boost-rule";

    /**
     * Find {@link EsBoostRule} by the configuration it belongs to.
     *
     * @param advancedSearchConfig
     * @return
     */
    List<EsBoostRule> findByAdvancedSearchConfig(EsAdvancedSearchConfig advancedSearchConfig);
}
