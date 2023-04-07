package digi.ecomm.commercesearch.client.impl;

import digi.ecomm.commercesearch.client.EsContext;
import digi.ecomm.commercesearch.client.EsContextFactory;
import digi.ecomm.commercesearch.exception.NoValidElasticsearchConfigException;
import digi.ecomm.commercesearch.repository.EsAdvancedSearchConfigRepository;
import digi.ecomm.commercesearch.repository.EsFacetSearchConfigRepository;
import digi.ecomm.commercesearch.repository.EsIndexRepository;
import digi.ecomm.commercesearch.repository.EsIndexedPropertyRepository;
import digi.ecomm.entity.commercesearch.EsFacetSearchConfig;
import digi.ecomm.entity.commercesearch.EsIndex;
import digi.ecomm.entity.commercesearch.EsIndexedEntityType;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.entity.commercesearch.advance.EsAdvancedSearchConfig;
import digi.ecomm.platformservice.util.ServicesUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class EsContextFactoryImpl implements EsContextFactory {
    private final EsFacetSearchConfigRepository facetSearchConfigRepository;
    private final EsIndexRepository indexRepository;
    private final EsIndexedPropertyRepository indexedPropertyRepository;
    private final EsAdvancedSearchConfigRepository advancedSearchConfigRepository;

    public EsContextFactoryImpl(
            final EsFacetSearchConfigRepository facetSearchConfigRepository,
            final EsIndexRepository indexRepository,
            final EsIndexedPropertyRepository indexedPropertyRepository,
            final EsAdvancedSearchConfigRepository advancedSearchConfigRepository) {
        this.facetSearchConfigRepository = facetSearchConfigRepository;
        this.indexRepository = indexRepository;
        this.indexedPropertyRepository = indexedPropertyRepository;
        this.advancedSearchConfigRepository = advancedSearchConfigRepository;
    }

    @Override
    public EsContext get(final String facetSearchConfigName, final EsIndexedEntityType indexedEntityType)
            throws NoValidElasticsearchConfigException {
        ServicesUtils.validateParameterNotNullStandardMessage("facetSearchConfigName", facetSearchConfigName);
        ServicesUtils.validateParameterNotNullStandardMessage("indexedEntityType", indexedEntityType);

        final EsFacetSearchConfig facetSearchConfig = facetSearchConfigRepository.findByName(facetSearchConfigName);
        if (facetSearchConfig == null) {
            throw new NoValidElasticsearchConfigException("No valid EsFacetSearchConfig configured.");
        }

        final EsIndex index = indexRepository.findByFacetSearchConfigAndIndexedEntityType(facetSearchConfig, indexedEntityType);
        if (index == null) {
            throw new NoValidElasticsearchConfigException("No valid EsIndex configured.");
        }

        final List<EsIndexedProperty> indexedProperties = indexedPropertyRepository.findByIndex(index);
        if (CollectionUtils.isEmpty(indexedProperties)) {
            throw new NoValidElasticsearchConfigException("No valid EsIndexedProperty configured.");
        }

        final EsAdvancedSearchConfig advancedSearchConfig = advancedSearchConfigRepository.findByFacetSearchConfig(facetSearchConfig);
        if (advancedSearchConfig == null) {
            throw new NoValidElasticsearchConfigException("No valid EsAdvancedSearchConfig configured.");
        }

        final EsContext context = new EsContext();
        context.setFacetSearchConfig(facetSearchConfig);
        context.setIndex(index);
        context.setIndexedProperties(indexedProperties);
        context.setAdvancedSearchConfig(advancedSearchConfig);

        return context;
    }
}
