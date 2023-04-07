package digi.ecomm.commercesearch.index.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import digi.ecomm.commercesearch.client.EsClientFactory;
import digi.ecomm.commercesearch.client.EsContext;
import digi.ecomm.commercesearch.client.EsContextFactory;
import digi.ecomm.commercesearch.data.IndexedPropertyGroup;
import digi.ecomm.commercesearch.exception.NoValidElasticsearchConfigException;
import digi.ecomm.commercesearch.index.data.*;
import digi.ecomm.commercesearch.index.provider.FieldValueProvider;
import digi.ecomm.commercesearch.index.provider.data.IndexedProduct;
import digi.ecomm.commercesearch.index.service.IndexService;
import digi.ecomm.commercesearch.provider.IndexedSourceProvider;
import digi.ecomm.commercesearch.repository.EsStopWordRepository;
import digi.ecomm.commercesearch.repository.EsSynonymConfigRepository;
import digi.ecomm.commercesearch.strategy.EsFacetSearchConfigSelectionStrategy;
import digi.ecomm.commercesearch.strategy.EsIndexedPropertyGroupStrategy;
import digi.ecomm.datatransferobject.search.AbstractIndexedSourceItem;
import digi.ecomm.entity.commercesearch.*;
import digi.ecomm.platformservice.util.BeanUtils;
import digi.ecomm.platformservice.util.ServicesUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.core.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class IndexServiceImpl implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexServiceImpl.class);

    private static final String DOT = ".";
    private static final String INDEXING_BULK_PROCESSOR_NAME_PREFIX = "indexing-bulk-processor-";
    private static final String CONF_NUMBER_OF_SHARDS = "index.number_of_shards";
    private static final String CONF_NUMBER_OF_REPLICAS = "index.number_of_replicas";
    private static final String MAPPING_DYNAMIC = "dynamic";
    private static final String MAPPING_PROPERTIES = "properties";
    private static final String MAPPING_TYPE = "type";
    private static final String MAPPING_INDEX = "index";
    private static final String MAPPING_MULTI_FIELDS = "fields";

    private static ObjectMapper objectMapper = new ObjectMapper();

    private final EsClientFactory clientFactory;
    private final EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy;
    private final EsStopWordRepository stopWordRepository;
    private final EsSynonymConfigRepository synonymConfigRepository;

    private final EsContextFactory contextFactory;
    private final EsFacetSearchConfigSelectionStrategy facetSearchConfigSelectionStrategy;
    private final IndexedSourceProvider<IndexedProduct> indexedSourceProvider;

    public IndexServiceImpl(final EsClientFactory clientFactory, final EsIndexedPropertyGroupStrategy indexedPropertyGroupStrategy,
                            final EsStopWordRepository stopWordRepository, final EsSynonymConfigRepository synonymConfigRepository,
                            final EsContextFactory contextFactory,
                            final EsFacetSearchConfigSelectionStrategy facetSearchConfigSelectionStrategy,
                            final IndexedSourceProvider<IndexedProduct> indexedSourceProvider) {
        this.clientFactory = clientFactory;
        this.indexedPropertyGroupStrategy = indexedPropertyGroupStrategy;
        this.stopWordRepository = stopWordRepository;
        this.synonymConfigRepository = synonymConfigRepository;
        this.contextFactory = contextFactory;
        this.facetSearchConfigSelectionStrategy = facetSearchConfigSelectionStrategy;
        this.indexedSourceProvider = indexedSourceProvider;
    }

    @Override
    public void fullIndexing() throws IOException, NoValidElasticsearchConfigException {
        final EsContext context = contextFactory
                .get(facetSearchConfigSelectionStrategy.getCurrentEsFacetSearchConfig(), EsIndexedEntityType.PRODUCT);
        final List<IndexedProduct> products = indexedSourceProvider.get();

        ServicesUtils.validateParameterNotNullStandardMessage("sourceObjects", products); //NOSONAR
        ServicesUtils.validateParameterNotNullStandardMessage("context", context); //NOSONAR

        final EsFacetSearchConfig facetSearchConfig = context.getFacetSearchConfig();
        final EsIndex index = context.getIndex();
        final RestHighLevelClient client = clientFactory.get(facetSearchConfig);
        if (isIndexExisting(index, client)) {
            deleteIndex(index, client);
        }
        createIndex(context, client);
        indexDocuments(products, context, client);
    }

    @Override
    public void updateDocument(final Long productId) throws IOException, NoValidElasticsearchConfigException {

        ServicesUtils.validateParameterNotNullStandardMessage("productId", productId); //NOSONAR

        final EsContext context = contextFactory
                .get(facetSearchConfigSelectionStrategy.getCurrentEsFacetSearchConfig(), EsIndexedEntityType.PRODUCT);
        final IndexedProduct sourceObject = indexedSourceProvider.get(productId);

        ServicesUtils.validateParameterNotNullStandardMessage("sourceObject", sourceObject); //NOSONAR
        ServicesUtils.validateParameterNotNullStandardMessage("context", context); //NOSONAR

        final EsFacetSearchConfig facetSearchConfig = context.getFacetSearchConfig();
        final RestHighLevelClient client = clientFactory.get(facetSearchConfig);
        final EsIndex index = context.getIndex();
        final Map<String, Object> sourceMap = objectMapper.convertValue(sourceObject, Map.class);
        client.update(new UpdateRequest(index.getName(), String.valueOf(sourceObject.getId())).doc(sourceMap), RequestOptions.DEFAULT);
    }

    @Override
    public void deleteDocument(final Long productId) throws IOException, NoValidElasticsearchConfigException {
        final EsContext context = contextFactory
                .get(facetSearchConfigSelectionStrategy.getCurrentEsFacetSearchConfig(), EsIndexedEntityType.PRODUCT);
        ServicesUtils.validateParameterNotNullStandardMessage("sourceObjectId", productId); //NOSONAR
        ServicesUtils.validateParameterNotNullStandardMessage("context", context); //NOSONAR

        final EsFacetSearchConfig facetSearchConfig = context.getFacetSearchConfig();
        final RestHighLevelClient client = clientFactory.get(facetSearchConfig);
        final EsIndex index = context.getIndex();
        final DeleteRequest request = new DeleteRequest(index.getName(), String.valueOf(productId));
        client.delete(request, RequestOptions.DEFAULT);
    }

    private void indexDocuments(final List<? extends AbstractIndexedSourceItem> sourceObjects, final EsContext context,
                                final RestHighLevelClient client) {

        if (CollectionUtils.isEmpty(sourceObjects)) {
            LOGGER.warn("List of indexed items is empty.");
            return;
        }

        final EsIndex index = context.getIndex();
        final List<EsIndexedProperty> indexedProperties = context.getIndexedProperties();
        final EsIndexConfig indexConfig = context.getFacetSearchConfig().getIndexConfig();

        final BulkProcessor.Builder builder = BulkProcessor.builder((request, bulkListener) ->
                        client.bulkAsync(request, RequestOptions.DEFAULT, bulkListener),
                new BulkProcessorListener(), INDEXING_BULK_PROCESSOR_NAME_PREFIX + index.getName());
        builder.setBulkActions(indexConfig.getBatchSize());
        builder.setBulkSize(new ByteSizeValue(indexConfig.getBatchBytes(), ByteSizeUnit.BYTES));
        builder.setConcurrentRequests(indexConfig.getNumConcurrentRequests());
        builder.setFlushInterval(TimeValue.timeValueSeconds(indexConfig.getFlushIntervalSeconds()));
        builder.setBackoffPolicy(BackoffPolicy
                .constantBackoff(TimeValue.timeValueSeconds(indexConfig.getRetryDelaySeconds()), indexConfig.getMaxRetries()));

        final BulkProcessor bulkProcessor = builder.build();
        sourceObjects.forEach(sourceObject -> {
            final IndexRequest indexRequest = mapSourceItemToIndexRequest(sourceObject, index.getName(), indexedProperties,
                    indexConfig.isDynamicMappingAllowed());
            bulkProcessor.add(indexRequest);
        });

        bulkProcessor.close();
    }

    private IndexRequest mapSourceItemToIndexRequest(final AbstractIndexedSourceItem sourceObject, final String indexName,
                                                     final List<EsIndexedProperty> indexedProperties, final boolean dynamicMappingAllowed) {

        final IndexRequest indexRequest = new IndexRequest(indexName).id(String.valueOf(sourceObject.getId()));
        final Map<String, Object> sourceMap = objectMapper.convertValue(sourceObject, Map.class);
        final List<Object> sources = new ArrayList<>();
        indexedProperties.stream().filter(property -> sourceMap.containsKey(property.getName())).forEach(property -> {
            final String fieldName = property.getName();
            final Object fieldValue;
            if (StringUtils.isNotBlank(property.getFieldValueProvider())) {
                fieldValue = BeanUtils.getBean(property.getFieldValueProvider(), FieldValueProvider.class)
                        .getFieldValues(property, sourceObject);
            } else {
                fieldValue = sourceMap.get(fieldName);
            }
            sources.add(fieldName);
            sources.add(fieldValue);
        });

        if (dynamicMappingAllowed && MapUtils.isNotEmpty(sourceObject.getDynamicFields())) {
            sourceObject.getDynamicFields().forEach((fieldName, fieldValue) -> {
                sources.add(fieldName);
                sources.add(fieldValue);
            });
        }
        return indexRequest.source(XContentType.JSON, sources.stream().toArray(Object[]::new));
    }

    private void createIndex(final EsContext context, final RestHighLevelClient client) throws IOException {
        final EsIndex index = context.getIndex();
        final EsServerConfig serverConfig = context.getFacetSearchConfig().getServerConfig();
        final CreateIndexRequest request = new CreateIndexRequest(index.getName());
        request.settings(createIndexSettings(serverConfig));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Index settings {}", request.settings());
        }

        final List<EsIndexedProperty> indexedProperties = context.getIndexedProperties();
        final List<IndexedPropertyGroup> indexedPropertyGroup = indexedPropertyGroupStrategy.group(indexedProperties);
        final Map<String, Object> indexMappings = createIndexMappings(indexedPropertyGroup);
        indexMappings.put(MAPPING_DYNAMIC, BooleanUtils.isTrue(context.getFacetSearchConfig().getIndexConfig().isDynamicMappingAllowed()));
        request.mapping(indexMappings);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Index mappings {}", objectMapper.writeValueAsString(indexMappings));
        }

        client.indices().create(request, RequestOptions.DEFAULT);
    }

    private Map<String, Object> createIndexMappings(final List<IndexedPropertyGroup> indexedPropertyGroups) {
        ServicesUtils.validateParameterNotNullStandardMessage("indexedPropertyGroups", indexedPropertyGroups);

        final Map<String, Object> indexMapping = new HashMap<>();
        final Map<String, Object> propertiesMapping = new HashMap<>();
        indexedPropertyGroups.forEach(propertyGroup -> {
            // Create the field itself mapping
            final Map<String, Object> fieldMapping = createFieldMapping(propertyGroup.getProperty());
            propertiesMapping.put(propertyGroup.getProperty().getName(), fieldMapping);

            // Create the nested field mapping if any
            if (CollectionUtils.isNotEmpty(propertyGroup.getNestedProperties())) {
                final Map<String, Object> nestedPropertiesMapping = new HashMap<>();
                propertyGroup.getNestedProperties().forEach(nestedProperty -> {
                    final Map<String, Object> nestedFieldMapping = createFieldMapping(nestedProperty);
                    nestedPropertiesMapping.put(StringUtils.substring(nestedProperty.getName(),
                            StringUtils.lastIndexOf(nestedProperty.getName(), DOT) + 1), nestedFieldMapping);
                });
                fieldMapping.put(MAPPING_PROPERTIES, nestedPropertiesMapping);
            }
        });
        indexMapping.put(MAPPING_PROPERTIES, propertiesMapping);
        return indexMapping;
    }

    private Map<String, Object> createFieldMapping(final EsIndexedProperty property) {
        ServicesUtils.validateParameterNotNullStandardMessage("property", property);

        final Map<String, Object> fieldMapping = new HashMap<>();
        fieldMapping.put(MAPPING_TYPE, property.getType().getValue());

        // Nested type doesn't support this parameter
        if (!Objects.equals(EsPropertyType.NESTED, property.getType())) {
            fieldMapping.put(MAPPING_INDEX, property.isIndexed());
        }

        // Multi fields mapping
        final EsPropertyType multiFieldsType = property.getMultiFieldsType();
        if (Objects.nonNull(multiFieldsType)) {
            fieldMapping.put(MAPPING_MULTI_FIELDS, Collections.singletonMap(multiFieldsType.getValue(),
                    Collections.singletonMap(MAPPING_TYPE, multiFieldsType.getValue())));
        }

        return fieldMapping;
    }

    private Settings.Builder createIndexSettings(final EsServerConfig serverConfig) {
        ServicesUtils.validateParameterNotNullStandardMessage("serverConfig", serverConfig);

        return Settings.builder()
                .put(CONF_NUMBER_OF_SHARDS, serverConfig.getNumShards())
                .put(CONF_NUMBER_OF_REPLICAS, serverConfig.getReplicationFactor())
                .loadFromMap(createIndexAnalysis(serverConfig.getFacetSearchConfig()));
    }

    private Map<String, Object> createIndexAnalysis(final EsFacetSearchConfig facetSearchConfig) {
        final IndexAnalyzer.IndexAnalyzerBuilder analyzerBuilder = IndexAnalyzer.builder();
        final List<EsStopWord> stopWords = stopWordRepository.findByFacetSearchConfig(facetSearchConfig);
        if (CollectionUtils.isNotEmpty(stopWords)) {
            analyzerBuilder.filter(StopWordFilter.builder()
                    .configName(facetSearchConfig.getName())
                    .stops(stopWords.stream().map(EsStopWord::getWord).collect(Collectors.toList()))
                    .build());
        }

        final List<EsSynonymConfig> synonymConfigs = synonymConfigRepository.findByFacetSearchConfig(facetSearchConfig);
        if (CollectionUtils.isNotEmpty(synonymConfigs)) {
            analyzerBuilder.filter(SynonymFilter.builder()
                    .configName(facetSearchConfig.getName())
                    .synonyms(synonymConfigs.stream()
                            .map(synonymConfig -> new Synonym(synonymConfig.getSynonymFrom(), synonymConfig.getSynonymTo()))
                            .collect(Collectors.toList()))
                    .build());
        }

        final IndexAnalysis analysis = IndexAnalysis.builder().analyzer(analyzerBuilder.build()).build();
        return analysis.toMap();
    }

    private void deleteIndex(final EsIndex index, final RestHighLevelClient client) throws IOException {
        ServicesUtils.validateParameterNotNullStandardMessage("index", index);  //NOSONAR
        ServicesUtils.validateParameterNotNullStandardMessage("client", client);

        client.indices().delete(new DeleteIndexRequest(index.getName()), RequestOptions.DEFAULT);
    }

    private boolean isIndexExisting(final EsIndex index, final RestHighLevelClient client) throws IOException {
        ServicesUtils.validateParameterNotNullStandardMessage("index", index); //NOSONAR
        ServicesUtils.validateParameterNotNullStandardMessage("client", client);

        return client.indices().exists(new GetIndexRequest(index.getName()), RequestOptions.DEFAULT);
    }

    private static class BulkProcessorListener implements BulkProcessor.Listener {

        @Override
        public void beforeBulk(final long executionId, final BulkRequest request) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Executing bulk [{}] with {} requests",
                        executionId, request.numberOfActions());
            }
        }

        @Override
        public void afterBulk(final long executionId, final BulkRequest request, final BulkResponse response) {
            if (response.hasFailures()) {
                LOGGER.warn("Bulk [{}] executed with failures", executionId);
                final String failureMessage = response.buildFailureMessage();
                LOGGER.warn(failureMessage);
            } else {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Bulk [{}] completed in {} milliseconds",
                            executionId, response.getTook().getMillis());
                }
            }
        }

        @Override
        public void afterBulk(final long executionId, final BulkRequest request, final Throwable failure) {
            LOGGER.error("Failed to execute bulk", failure);
        }
    }
}
