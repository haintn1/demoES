package digi.ecomm.commercesearch;

import digi.ecomm.commercesearch.repository.*;
import digi.ecomm.entity.commercesearch.*;
import digi.ecomm.entity.commercesearch.advance.*;
import digi.ecomm.platformservice.initialdata.setup.SampleDataCreator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class SearchSampleData implements SampleDataCreator {

    private static final String LOCAL_HOST = "localhost";

    private static final String UNDER_SCORE = "_";
    private static final String ATTRIBUTE_PREFIX = "attribute";

    @Autowired
    private EsValueRangeSetRepository valueRangeSetRepository;

    @Autowired
    private EsValueRangeRepository valueRangeRepository;

    @Autowired
    private EsFacetSearchConfigRepository facetSearchConfigRepository;

    @Autowired
    private EsIndexConfigRepository indexConfigRepository;

    @Autowired
    private EsSearchConfigRepository searchConfigRepository;

    @Autowired
    private EsServerConfigRepository serverConfigRepository;

    @Autowired
    private EsServerRepository serverRepository;

    @Autowired
    private EsIndexRepository indexRepository;

    @Autowired
    private EsSortRepository sortRepository;

    @Autowired
    private EsSortFieldRepository sortFieldRepository;

    @Autowired
    private EsIndexedPropertyRepository indexedPropertyRepository;

    @Autowired
    private EsAdvancedSearchConfigRepository advancedSearchConfigRepository;

    @Autowired
    private EsPromotedItemRepository promotedItemRepository;

    @Autowired
    private EsBoostRuleRepository boostRuleRepository;

    @Autowired
    private EsStopWordRepository stopWordRepository;

    @Autowired
    private EsSynonymConfigRepository synonymConfigRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SearchProperties searchProperties;

    @Override
    public void createData() {
        final EsValueRangeSet priceValueRangeSet = createPriceValueRangeSet();
        createPriceValueRanges(priceValueRangeSet);
        final EsFacetSearchConfig facetSearchConfig = createFacetSearchConfig();
        createIndexConfig(facetSearchConfig);
        createSearchConfig(facetSearchConfig);
        final EsServerConfig serverConfig = createServerConfig(facetSearchConfig);
        createServer(serverConfig);
        final EsIndex index = createIndex(facetSearchConfig);
        createSorts(index);
        createIndexedProperties(index, priceValueRangeSet);
        final EsAdvancedSearchConfig advancedSearchConfig = createAdvancedSearchConfig(facetSearchConfig);
        createPromotedItems(advancedSearchConfig);
        createBoostRules(advancedSearchConfig);
        createStopWords(facetSearchConfig);
        createSynonyms(facetSearchConfig);
    }


    private EsValueRangeSet createPriceValueRangeSet() {
        // Create EsValueRangeSet
        final String priceRange = "priceRange";
        EsValueRangeSet priceValueRangeSet = valueRangeSetRepository.findByName(priceRange);
        if (priceValueRangeSet == null) {
            priceValueRangeSet = new EsValueRangeSet();
            priceValueRangeSet.setName(priceRange);
            priceValueRangeSet.setType(EsValueRangeType.DOUBLE);
            valueRangeSetRepository.save(priceValueRangeSet);
        }
        return priceValueRangeSet;
    }

    private List<EsValueRange> createPriceValueRanges(final EsValueRangeSet priceValueRangeSet) {
        // Create EsValueRange
        final String range1Name = "$1 - $50";
        EsValueRange range1 = valueRangeRepository.findByNameAndValueRangeSet(range1Name, priceValueRangeSet);
        if (range1 == null) {
            range1 = new EsValueRange();
            range1.setName(range1Name);
            range1.setValueFrom("1");
            range1.setValueTo("50");
            range1.setValueRangeSet(priceValueRangeSet);
        }

        final String range2Name = "$51 - $150";
        EsValueRange range2 = valueRangeRepository.findByNameAndValueRangeSet(range2Name, priceValueRangeSet);
        if (range2 == null) {
            range2 = new EsValueRange();
            range2.setName(range2Name);
            range2.setValueFrom("51");
            range2.setValueTo("150");
            range2.setValueRangeSet(priceValueRangeSet);
        }

        final String range3Name = "$151 - $300";
        EsValueRange range3 = valueRangeRepository.findByNameAndValueRangeSet(range3Name, priceValueRangeSet);
        if (range3 == null) {
            range3 = new EsValueRange();
            range3.setName(range3Name);
            range3.setValueFrom("151");
            range3.setValueTo("300");
            range3.setValueRangeSet(priceValueRangeSet);
        }

        final String range4Name = "$301 - $500";
        EsValueRange range4 = valueRangeRepository.findByNameAndValueRangeSet(range4Name, priceValueRangeSet);
        if (range4 == null) {
            range4 = new EsValueRange();
            range4.setName(range4Name);
            range4.setValueFrom("301");
            range4.setValueTo("500");
            range4.setValueRangeSet(priceValueRangeSet);
        }

        final String range5Name = "$501 - $1,000";
        EsValueRange range5 = valueRangeRepository.findByNameAndValueRangeSet(range5Name, priceValueRangeSet);
        if (range5 == null) {
            range5 = new EsValueRange();
            range5.setName(range5Name);
            range5.setValueFrom("501");
            range5.setValueTo("1000");
            range5.setValueRangeSet(priceValueRangeSet);
        }

        final String range6Name = "$1,000+";
        EsValueRange range6 = valueRangeRepository.findByNameAndValueRangeSet(range6Name, priceValueRangeSet);
        if (range6 == null) {
            range6 = new EsValueRange();
            range6.setName(range6Name);
            range6.setValueFrom("1001");
            range6.setValueRangeSet(priceValueRangeSet);
        }

        final List<EsValueRange> priceValueRanges = Arrays.asList(range1, range2, range3, range4, range5, range6);
        valueRangeRepository.saveAll(priceValueRanges);
        return priceValueRanges;
    }

    private EsFacetSearchConfig createFacetSearchConfig() {
        // Create EsFacetSearchConfig
        final String productSearchConfigName = "ENTERPRISE_PRODUCTS";
        EsFacetSearchConfig facetSearchConfig = facetSearchConfigRepository.findByName(productSearchConfigName);
        if (facetSearchConfig == null) {
            facetSearchConfig = new EsFacetSearchConfig();
            facetSearchConfig.setName(productSearchConfigName);
            facetSearchConfig.setDescription("Elasticsearch Config for SF products");
            facetSearchConfigRepository.save(facetSearchConfig);
        }
        return facetSearchConfig;
    }

    private EsIndexConfig createIndexConfig(final EsFacetSearchConfig facetSearchConfig) {
        // Create EsIndexConfig
        final String indexConfigName = "indexConfig-enterprise";
        final int batchSize = 100;
        EsIndexConfig indexConfig = indexConfigRepository.findByName(indexConfigName);
        if (indexConfig == null) {
            indexConfig = new EsIndexConfig();
            indexConfig.setName(indexConfigName);
            indexConfig.setBatchSize(batchSize);
            indexConfig.setFacetSearchConfig(facetSearchConfig);
            indexConfigRepository.save(indexConfig);
        }
        return indexConfig;
    }

    private EsSearchConfig createSearchConfig(final EsFacetSearchConfig facetSearchConfig) {
        // Create EsSearchConfig
        EsSearchConfig searchConfig = searchConfigRepository.findByFacetSearchConfig(facetSearchConfig);
        if (searchConfig == null) {
            final int pageSize = 20;
            final int maxBuckets = 1000;
            searchConfig = new EsSearchConfig();
            searchConfig.setPageSize(pageSize);
            searchConfig.setMaxBuckets(maxBuckets);
            searchConfig.setDescription("Elasticsearch search configuration");
            searchConfig.setFacetSearchConfig(facetSearchConfig);
            searchConfigRepository.save(searchConfig);
        }
        return searchConfig;
    }

    private EsServerConfig createServerConfig(final EsFacetSearchConfig facetSearchConfig) {
        // Create EsServerConfig
        EsServerConfig serverConfig = serverConfigRepository.findByName(LOCAL_HOST);
        if (serverConfig == null) {
            serverConfig = new EsServerConfig();
            serverConfig.setName(LOCAL_HOST);
            serverConfig.setFacetSearchConfig(facetSearchConfig);
            serverConfigRepository.save(serverConfig);
        }
        return serverConfig;
    }

    private EsServer createServer(final EsServerConfig serverConfig) {
        // Create EsServer
        EsServer server = serverRepository.findByNameAndServerConfig(LOCAL_HOST, serverConfig);
        if (server == null) {
            final int port = 9200;
            server = new EsServer();
            server.setName(LOCAL_HOST);
            server.setScheme("http");
            server.setHostName(LOCAL_HOST);
            server.setPort(port);
            server.setServerConfig(serverConfig);
            serverRepository.save(server);
        }
        return server;
    }

    private EsIndex createIndex(final EsFacetSearchConfig facetSearchConfig) {
        // Create EsIndex
        final String productIndexName = "enterprise_product";
        EsIndex index = indexRepository.findByName(productIndexName);
        if (index == null) {
            index = new EsIndex();
            index.setName(productIndexName);
            index.setIndexedEntityType(EsIndexedEntityType.PRODUCT);
            index.setFacetSearchConfig(facetSearchConfig);
            indexRepository.save(index);
        }
        return index;
    }

    private List<EsSort> createSorts(final EsIndex index) {
        // Create EsSort

        final String priceAscCode = "price-asc";
        EsSort sort2 = sortRepository.findByCodeAndIndex(priceAscCode, index);
        if (sort2 == null) {
            sort2 = new EsSort();
            sort2.setCode(priceAscCode);
            sort2.setName("Price (Low-High)");
            sort2.setUseBoost(false);
            sort2.setIndex(index);
            sortRepository.save(sort2);
        }

        final String priceDescCode = "price-desc";
        EsSort sort3 = sortRepository.findByCodeAndIndex(priceDescCode, index);
        if (sort3 == null) {
            sort3 = new EsSort();
            sort3.setCode(priceDescCode);
            sort3.setName("Price (High-Low)");
            sort3.setUseBoost(false);
            sort3.setIndex(index);
            sortRepository.save(sort3);
        }

        // Create EsSortField

        final String priceFieldName = "price";
        EsSortField sortField2 = sortFieldRepository.findByFieldNameAndAscending(priceFieldName, Boolean.TRUE);
        if (sortField2 == null) {
            sortField2 = new EsSortField();
            sortField2.setSort(sort2);
            sortField2.setFieldName(priceFieldName);
            sortField2.setFieldNameProvider("priceGroupAwareSortFieldNameProvider");
            sortField2.setAscending(Boolean.TRUE);
            sortFieldRepository.save(sortField2);
        }

        EsSortField sortField3 = sortFieldRepository.findByFieldNameAndAscending(priceFieldName, Boolean.FALSE);
        if (sortField3 == null) {
            sortField3 = new EsSortField();
            sortField3.setSort(sort3);
            sortField3.setFieldName(priceFieldName);
            sortField3.setAscending(Boolean.FALSE);
            sortFieldRepository.save(sortField3);
        }

        Map<String, EsSort> skuSort = saveSort(index, "Part Number", "sku");
        Map<String, EsSort> descriptionSort = saveSort(index, "Product Desc.", "description");
        return Arrays.asList(sort2, sort3, skuSort.get("asc"), skuSort.get("desc"), descriptionSort.get("asc"),
                descriptionSort.get("desc"));
    }

    private Map<String, EsSort> saveSort(final EsIndex index, final String sortName, final String keySort) {
        Map<String, EsSort> result = new HashMap<>();
        final String descCode = String.format("%s-asc", keySort.toLowerCase());
        EsSort descSort = sortRepository.findByCodeAndIndex(descCode, index);
        if (descSort == null) {
            descSort = new EsSort();
            descSort.setCode(descCode);
            descSort.setName(String.format("%s (A-Z)", sortName));
            descSort.setUseBoost(false);
            descSort.setIndex(index);
            sortRepository.save(descSort);
        }

        final String ascCode = String.format("%s-desc", keySort.toLowerCase());
        EsSort ascSort = sortRepository.findByCodeAndIndex(ascCode, index);
        if (ascSort == null) {
            ascSort = new EsSort();
            ascSort.setCode(ascCode);
            ascSort.setName(String.format("%s (Z-A)", sortName));
            ascSort.setUseBoost(false);
            ascSort.setIndex(index);
            sortRepository.save(ascSort);
        }

        

        final String fieldName = keySort.toLowerCase();
        EsSortField sortField1 = sortFieldRepository.findByFieldNameAndAscending(fieldName, Boolean.TRUE);
        if (sortField1 == null) {
            sortField1 = new EsSortField();
            sortField1.setSort(descSort);
            sortField1.setFieldName(fieldName);
            sortField1.setFieldNameProvider("priceGroupAwareSortFieldNameProvider");
            sortField1.setAscending(Boolean.TRUE);
            sortFieldRepository.save(sortField1);
        }

        EsSortField sortField2 = sortFieldRepository.findByFieldNameAndAscending(fieldName, Boolean.FALSE);
        if (sortField2 == null) {
            sortField2 = new EsSortField();
            sortField2.setSort(ascSort);
            sortField2.setFieldName(fieldName);
            sortField2.setAscending(Boolean.FALSE);
            sortFieldRepository.save(sortField2);
        }
        result.put("desc", descSort);
        result.put("asc", ascSort);
        return result;
    }

    @SuppressWarnings({"MethodLength"})
    private void createIndexedProperties(final EsIndex index, final EsValueRangeSet priceValueRangeSet) {
        // Create EsIndexedProperty
        final String idPropName = "id";
        EsIndexedProperty idProp = indexedPropertyRepository.findByNameAndIndex(idPropName, index);
        if (idProp == null) {
            idProp = new EsIndexedProperty();
            idProp.setName(idPropName);
            idProp.setType(EsPropertyType.KEYWORD);
            idProp.setIndexed(false);
            idProp.setIndex(index);
            indexedPropertyRepository.save(idProp);
        }

        final String productUuidPropName = "uuid";
        EsIndexedProperty productUuidProp = indexedPropertyRepository.findByNameAndIndex(productUuidPropName, index);
        if (productUuidProp == null) {
            productUuidProp = new EsIndexedProperty();
            productUuidProp.setName(productUuidPropName);
            productUuidProp.setType(EsPropertyType.KEYWORD);
            productUuidProp.setIndex(index);
            indexedPropertyRepository.save(productUuidProp);
        }

        final String productNamePropName = "name";
        EsIndexedProperty productNameProp = indexedPropertyRepository.findByNameAndIndex(productNamePropName, index);
        if (productNameProp == null) {
            final float boost = 5f;
            productNameProp = new EsIndexedProperty();
            productNameProp.setName(productNamePropName);
            productNameProp.setType(EsPropertyType.TEXT);
            productNameProp.setMultiFieldsType(EsPropertyType.COMPLETION);
            productNameProp.setFtsQuery(true);
            productNameProp.setFtsFuzzyQuery(true);
            productNameProp.setFtsQueryBoost(boost);
            productNameProp.setUseForAutoCompletion(true);
            productNameProp.setIndex(index);
            indexedPropertyRepository.save(productNameProp);
        }

        final String productDescPropName = "description";
        EsIndexedProperty productDescProp = indexedPropertyRepository.findByNameAndIndex(productDescPropName, index);
        if (productDescProp == null) {
            productDescProp = new EsIndexedProperty();
            productDescProp.setName(productDescPropName);
            productDescProp.setType(EsPropertyType.KEYWORD);
            productDescProp.setFtsQuery(true);
            productDescProp.setFtsFuzzyQuery(true);
            productDescProp.setIndex(index);
            indexedPropertyRepository.save(productDescProp);
        }

        final String productImgPropName = "image";
        EsIndexedProperty productImgProp = indexedPropertyRepository.findByNameAndIndex(productImgPropName, index);
        if (productImgProp == null) {
            productImgProp = new EsIndexedProperty();
            productImgProp.setName(productImgPropName);
            productImgProp.setType(EsPropertyType.KEYWORD);
            productImgProp.setIndexed(false);
            productImgProp.setIndex(index);
            indexedPropertyRepository.save(productImgProp);
        }

        final String productSkuPropName = "sku";
        EsIndexedProperty productSkuProp = indexedPropertyRepository.findByNameAndIndex(productSkuPropName, index);
        if (productSkuProp == null) {
            productSkuProp = new EsIndexedProperty();
            productSkuProp.setName(productSkuPropName);
            productSkuProp.setType(EsPropertyType.KEYWORD);
            productSkuProp.setFtsQuery(false);
            productSkuProp.setIndex(index);
            indexedPropertyRepository.save(productSkuProp);
        }

        final String productPricePropName = "price";
        EsIndexedProperty productPriceProp = indexedPropertyRepository.findByNameAndIndex(productPricePropName, index);
        if (productPriceProp == null) {
            productPriceProp = new EsIndexedProperty();
            productPriceProp.setName(productPricePropName);
            productPriceProp.setDisplayName("Pricing");
            productPriceProp.setType(EsPropertyType.DOUBLE);
            productPriceProp.setFtsQuery(false);
            productPriceProp.setFacet(true);
            productPriceProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            productPriceProp.setFieldNameProvider("priceGroupAwareFieldNameProvider");
            productPriceProp.setValueRangeSet(priceValueRangeSet);
            productPriceProp.setPriority(100);
            productPriceProp.setIndex(index);
            indexedPropertyRepository.save(productPriceProp);
        }

        final String productMinPricePropName = "minPrice";
        EsIndexedProperty productMinPriceProp = indexedPropertyRepository.findByNameAndIndex(productMinPricePropName, index);
        if (productMinPriceProp == null) {
            productMinPriceProp = new EsIndexedProperty();
            productMinPriceProp.setName(productMinPricePropName);
            productMinPriceProp.setDisplayName("Pricing");
            productMinPriceProp.setType(EsPropertyType.DOUBLE);
            productMinPriceProp.setFtsQuery(false);
            productMinPriceProp.setFacet(false);
            productMinPriceProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            productMinPriceProp.setFieldNameProvider("priceGroupAwareFieldNameProvider");
            productMinPriceProp.setIndex(index);
            indexedPropertyRepository.save(productMinPriceProp);
        }

        final String productMaxPricePropName = "maxPrice";
        EsIndexedProperty productMaxPriceProp = indexedPropertyRepository.findByNameAndIndex(productMaxPricePropName, index);
        if (productMaxPriceProp == null) {
            productMaxPriceProp = new EsIndexedProperty();
            productMaxPriceProp.setName(productMaxPricePropName);
            productMaxPriceProp.setDisplayName("Pricing");
            productMaxPriceProp.setType(EsPropertyType.DOUBLE);
            productMaxPriceProp.setFtsQuery(false);
            productMaxPriceProp.setFacet(false);
            productMaxPriceProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            productMaxPriceProp.setFieldNameProvider("priceGroupAwareFieldNameProvider");
            productMaxPriceProp.setIndex(index);
            indexedPropertyRepository.save(productMaxPriceProp);
        }

        final String productTypePropName = "type";
        EsIndexedProperty productTypeProp = indexedPropertyRepository.findByNameAndIndex(productTypePropName, index);
        if (productTypeProp == null) {
            productTypeProp = new EsIndexedProperty();
            productTypeProp.setName(productTypePropName);
            productTypeProp.setType(EsPropertyType.INTEGER);
            productTypeProp.setFtsQuery(false);
            productTypeProp.setIndex(index);
            indexedPropertyRepository.save(productTypeProp);
        }

        final String categoryNamesPropName = "categoryNames";
        EsIndexedProperty categoryNamesProp = indexedPropertyRepository.findByNameAndIndex(categoryNamesPropName, index);
        if (categoryNamesProp == null) {
            categoryNamesProp = new EsIndexedProperty();
            categoryNamesProp.setName(categoryNamesPropName);
            categoryNamesProp.setDisplayName("Categories");
            categoryNamesProp.setType(EsPropertyType.TEXT);
            categoryNamesProp.setMultiFieldsType(EsPropertyType.KEYWORD);
            categoryNamesProp.setFtsQuery(true);
            categoryNamesProp.setFacet(true);
            categoryNamesProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            categoryNamesProp.setPriority(200);
            categoryNamesProp.setIndex(index);
            indexedPropertyRepository.save(categoryNamesProp);
        }

        final String groupNamesPropName = "groupNames";
        EsIndexedProperty groupNamesProp = indexedPropertyRepository.findByNameAndIndex(groupNamesPropName, index);
        if (groupNamesProp == null) {
            groupNamesProp = new EsIndexedProperty();
            groupNamesProp.setName(groupNamesPropName);
            groupNamesProp.setDisplayName("Groups");
            groupNamesProp.setType(EsPropertyType.TEXT);
            groupNamesProp.setMultiFieldsType(EsPropertyType.KEYWORD);
            groupNamesProp.setFtsQuery(false);
            groupNamesProp.setFacet(true);
            groupNamesProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            groupNamesProp.setPriority(100);
            groupNamesProp.setIndex(index);
            indexedPropertyRepository.save(groupNamesProp);
        }

        final String productSuggestNamePropName = "suggestName";
        EsIndexedProperty productSuggestNameProp = indexedPropertyRepository.findByNameAndIndex(productSuggestNamePropName, index);
        if (productSuggestNameProp == null) {
            productSuggestNameProp = new EsIndexedProperty();
            productSuggestNameProp.setName(productSuggestNamePropName);
            productSuggestNameProp.setType(EsPropertyType.TEXT);
            productSuggestNameProp.setFtsQuery(false);
            productSuggestNameProp.setIndex(index);
            indexedPropertyRepository.save(productSuggestNameProp);
        }

        final String productDiscountPropName = "discount";
        EsIndexedProperty productDiscountProp = indexedPropertyRepository.findByNameAndIndex(productDiscountPropName, index);
        if (productDiscountProp == null) {
            productDiscountProp = new EsIndexedProperty();
            productDiscountProp.setName(productDiscountPropName);
            productDiscountProp.setType(EsPropertyType.KEYWORD);
            productDiscountProp.setFtsQuery(false);
            productDiscountProp.setIndex(index);
            indexedPropertyRepository.save(productDiscountProp);
        }

        final String isBaseProductPropName = "isBaseProduct";
        EsIndexedProperty isBaseProductProp = indexedPropertyRepository.findByNameAndIndex(isBaseProductPropName, index);
        if (isBaseProductProp == null) {
            isBaseProductProp = new EsIndexedProperty();
            isBaseProductProp.setName(isBaseProductPropName);
            isBaseProductProp.setType(EsPropertyType.KEYWORD);
            isBaseProductProp.setFtsQuery(false);
            isBaseProductProp.setFacet(true);
            isBaseProductProp.setIncludeInFacets(false);
            isBaseProductProp.setIndex(index);
            indexedPropertyRepository.save(isBaseProductProp);
        }

        final String variationARCPropName = "variation_ARC";
        EsIndexedProperty variationARCProp = indexedPropertyRepository.findByNameAndIndex(variationARCPropName, index);
        if (variationARCProp == null) {
            variationARCProp = new EsIndexedProperty();
            variationARCProp.setName(variationARCPropName);
            variationARCProp.setDisplayName("ARC");
            variationARCProp.setType(EsPropertyType.KEYWORD);
            variationARCProp.setFtsQuery(true);
            variationARCProp.setFacet(true);
            variationARCProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            variationARCProp.setPriority(50);
            variationARCProp.setIndex(index);
            indexedPropertyRepository.save(variationARCProp);
        }

        final String variationCOLORPropName = "variation_COLOR";
        EsIndexedProperty variationCOLORProp = indexedPropertyRepository.findByNameAndIndex(variationCOLORPropName, index);
        if (variationCOLORProp == null) {
            variationCOLORProp = new EsIndexedProperty();
            variationCOLORProp.setName(variationCOLORPropName);
            variationCOLORProp.setDisplayName("COLOR");
            variationCOLORProp.setType(EsPropertyType.KEYWORD);
            variationCOLORProp.setFtsQuery(true);
            variationCOLORProp.setFacet(true);
            variationCOLORProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            variationCOLORProp.setPriority(48);
            variationCOLORProp.setIndex(index);
            indexedPropertyRepository.save(variationCOLORProp);
        }

        final String variationSIZEPropName = "variation_SIZE";
        EsIndexedProperty variationSIZEProp = indexedPropertyRepository.findByNameAndIndex(variationSIZEPropName, index);
        if (variationSIZEProp == null) {
            variationSIZEProp = new EsIndexedProperty();
            variationSIZEProp.setName(variationSIZEPropName);
            variationSIZEProp.setDisplayName("SIZE");
            variationSIZEProp.setType(EsPropertyType.KEYWORD);
            variationSIZEProp.setFtsQuery(true);
            variationSIZEProp.setFacet(true);
            variationSIZEProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            variationSIZEProp.setPriority(46);
            variationSIZEProp.setIndex(index);
            indexedPropertyRepository.save(variationSIZEProp);
        }

        final String variationFINISHPropName = "variation_FINISH";
        EsIndexedProperty variationFINISHProp = indexedPropertyRepository.findByNameAndIndex(variationFINISHPropName, index);
        if (variationFINISHProp == null) {
            variationFINISHProp = new EsIndexedProperty();
            variationFINISHProp.setName(variationFINISHPropName);
            variationFINISHProp.setDisplayName("FINISH");
            variationFINISHProp.setType(EsPropertyType.KEYWORD);
            variationFINISHProp.setFtsQuery(true);
            variationFINISHProp.setFacet(true);
            variationFINISHProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            variationFINISHProp.setPriority(44);
            variationFINISHProp.setIndex(index);
            indexedPropertyRepository.save(variationFINISHProp);
        }

        final String variationCOLORTEMPPropName = "variation_COLORTEMP";
        EsIndexedProperty variationCOLORTEMPProp = indexedPropertyRepository.findByNameAndIndex(variationCOLORTEMPPropName, index);
        if (variationCOLORTEMPProp == null) {
            variationCOLORTEMPProp = new EsIndexedProperty();
            variationCOLORTEMPProp.setName(variationCOLORTEMPPropName);
            variationCOLORTEMPProp.setDisplayName("COLOR TEMP");
            variationCOLORTEMPProp.setType(EsPropertyType.KEYWORD);
            variationCOLORTEMPProp.setFtsQuery(true);
            variationCOLORTEMPProp.setFacet(true);
            variationCOLORTEMPProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            variationCOLORTEMPProp.setPriority(42);
            variationCOLORTEMPProp.setIndex(index);
            indexedPropertyRepository.save(variationCOLORTEMPProp);
        }

        final String variationBLACKPropName = "variation_BLACK";
        EsIndexedProperty variationBLACKProp = indexedPropertyRepository.findByNameAndIndex(variationBLACKPropName, index);
        if (variationBLACKProp == null) {
            variationBLACKProp = new EsIndexedProperty();
            variationBLACKProp.setName(variationBLACKPropName);
            variationBLACKProp.setDisplayName("BLACK");
            variationBLACKProp.setType(EsPropertyType.KEYWORD);
            variationBLACKProp.setFtsQuery(true);
            variationBLACKProp.setFacet(true);
            variationBLACKProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            variationBLACKProp.setPriority(40);
            variationBLACKProp.setIndex(index);
            indexedPropertyRepository.save(variationBLACKProp);
        }

        final String variationRADIUSPropName = "variation_RADIUS";
        EsIndexedProperty variationRADIUSProp = indexedPropertyRepository.findByNameAndIndex(variationRADIUSPropName, index);
        if (variationRADIUSProp == null) {
            variationRADIUSProp = new EsIndexedProperty();
            variationRADIUSProp.setName(variationRADIUSPropName);
            variationRADIUSProp.setDisplayName("RADIUS");
            variationRADIUSProp.setType(EsPropertyType.KEYWORD);
            variationRADIUSProp.setFtsQuery(true);
            variationRADIUSProp.setFacet(true);
            variationRADIUSProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            variationRADIUSProp.setPriority(38);
            variationRADIUSProp.setIndex(index);
            indexedPropertyRepository.save(variationRADIUSProp);
        }

        final String variationBEAMSPREADPropName = "variation_BEAMSPREAD";
        EsIndexedProperty variationBEAMSPREADProp = indexedPropertyRepository.findByNameAndIndex(variationBEAMSPREADPropName, index);
        if (variationBEAMSPREADProp == null) {
            variationBEAMSPREADProp = new EsIndexedProperty();
            variationBEAMSPREADProp.setName(variationBEAMSPREADPropName);
            variationBEAMSPREADProp.setDisplayName("BEAM SPREAD");
            variationBEAMSPREADProp.setType(EsPropertyType.KEYWORD);
            variationBEAMSPREADProp.setFtsQuery(true);
            variationBEAMSPREADProp.setFacet(true);
            variationBEAMSPREADProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            variationBEAMSPREADProp.setPriority(36);
            variationBEAMSPREADProp.setIndex(index);
            indexedPropertyRepository.save(variationBEAMSPREADProp);
        }

        final String variationLAMPPropName = "variation_LAMP";
        EsIndexedProperty variationLAMPProp = indexedPropertyRepository.findByNameAndIndex(variationLAMPPropName, index);
        if (variationLAMPProp == null) {
            variationLAMPProp = new EsIndexedProperty();
            variationLAMPProp.setName(variationLAMPPropName);
            variationLAMPProp.setDisplayName("LAMP");
            variationLAMPProp.setType(EsPropertyType.KEYWORD);
            variationLAMPProp.setFtsQuery(true);
            variationLAMPProp.setFacet(true);
            variationLAMPProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            variationLAMPProp.setPriority(34);
            variationLAMPProp.setIndex(index);
            indexedPropertyRepository.save(variationLAMPProp);
        }

        final String variationOPTIONPropName = "variation_OPTION";
        EsIndexedProperty variationOPTIONProp = indexedPropertyRepository.findByNameAndIndex(variationOPTIONPropName, index);
        if (variationOPTIONProp == null) {
            variationOPTIONProp = new EsIndexedProperty();
            variationOPTIONProp.setName(variationOPTIONPropName);
            variationOPTIONProp.setDisplayName("OPTION");
            variationOPTIONProp.setType(EsPropertyType.KEYWORD);
            variationOPTIONProp.setFtsQuery(true);
            variationOPTIONProp.setFacet(true);
            variationOPTIONProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            variationOPTIONProp.setPriority(32);
            variationOPTIONProp.setIndex(index);
            indexedPropertyRepository.save(variationOPTIONProp);
        }

        final String variationMAXLUMENSPropName = "variation_MAXLUMENS";
        EsIndexedProperty variationMAXLUMENSProp = indexedPropertyRepository.findByNameAndIndex(variationMAXLUMENSPropName, index);
        if (variationMAXLUMENSProp == null) {
            variationMAXLUMENSProp = new EsIndexedProperty();
            variationMAXLUMENSProp.setName(variationMAXLUMENSPropName);
            variationMAXLUMENSProp.setDisplayName("MAX LUMENS");
            variationMAXLUMENSProp.setType(EsPropertyType.KEYWORD);
            variationMAXLUMENSProp.setFtsQuery(true);
            variationMAXLUMENSProp.setFacet(true);
            variationMAXLUMENSProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            variationMAXLUMENSProp.setPriority(30);
            variationMAXLUMENSProp.setIndex(index);
            indexedPropertyRepository.save(variationMAXLUMENSProp);
        }

        final String uomsPropName = "uoms";
        EsIndexedProperty uomsProductProp = indexedPropertyRepository.findByNameAndIndex(uomsPropName, index);
        if (uomsProductProp == null) {
            uomsProductProp = new EsIndexedProperty();
            uomsProductProp.setName(uomsPropName);
            uomsProductProp.setType(EsPropertyType.NESTED);
            uomsProductProp.setFtsQuery(false);
            uomsProductProp.setFacet(true);
            uomsProductProp.setIncludeInFacets(false);
            uomsProductProp.setIndex(index);
            indexedPropertyRepository.save(uomsProductProp);
        }

        final String defaultUomPropName = "defaultUOM";
        EsIndexedProperty defaultUomProductProp = indexedPropertyRepository.findByNameAndIndex(defaultUomPropName, index);
        if (defaultUomProductProp == null) {
            defaultUomProductProp = new EsIndexedProperty();
            defaultUomProductProp.setName(defaultUomPropName);
            defaultUomProductProp.setType(EsPropertyType.KEYWORD);
            defaultUomProductProp.setFtsQuery(false);
            defaultUomProductProp.setFacet(true);
            defaultUomProductProp.setIncludeInFacets(false);
            defaultUomProductProp.setIndex(index);
            indexedPropertyRepository.save(defaultUomProductProp);
        }

        final String minPackQtyPropName = "minPackQty";
        EsIndexedProperty  minPackQtyProductProp = indexedPropertyRepository.findByNameAndIndex(minPackQtyPropName, index);
        if (minPackQtyProductProp == null) {
            minPackQtyProductProp = new EsIndexedProperty();
            minPackQtyProductProp.setName(minPackQtyPropName);
            minPackQtyProductProp.setType(EsPropertyType.KEYWORD);
            minPackQtyProductProp.setFtsQuery(false);
            minPackQtyProductProp.setFacet(true);
            minPackQtyProductProp.setIncludeInFacets(false);
            minPackQtyProductProp.setIndex(index);
            indexedPropertyRepository.save(minPackQtyProductProp);
        }

        final String branchIdPropName = "branchId";
        EsIndexedProperty  branchIdProductProp = indexedPropertyRepository.findByNameAndIndex(branchIdPropName, index);
        if (branchIdProductProp == null) {
            branchIdProductProp = new EsIndexedProperty();
            branchIdProductProp.setName(branchIdPropName);
            branchIdProductProp.setDisplayName("Brand");
            branchIdProductProp.setType(EsPropertyType.TEXT);
            branchIdProductProp.setMultiFieldsType(EsPropertyType.KEYWORD);
            branchIdProductProp.setFtsQuery(true);
            branchIdProductProp.setFacet(true);
            branchIdProductProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            branchIdProductProp.setPriority(300);
            branchIdProductProp.setIndex(index);
            indexedPropertyRepository.save(branchIdProductProp);
        }

        final String colourPropName = "colour";
        EsIndexedProperty  colourProductProp = indexedPropertyRepository.findByNameAndIndex(colourPropName, index);
        if (colourProductProp == null) {
            colourProductProp = new EsIndexedProperty();
            colourProductProp.setName(colourPropName);
            colourProductProp.setDisplayName("Colour");
            colourProductProp.setType(EsPropertyType.TEXT);
            colourProductProp.setMultiFieldsType(EsPropertyType.KEYWORD);
            colourProductProp.setFtsQuery(true);
            colourProductProp.setFacet(true);
            colourProductProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            colourProductProp.setPriority(400);
            colourProductProp.setIndex(index);
            indexedPropertyRepository.save(colourProductProp);
        }

        final String stockPropName = "stock";
        EsIndexedProperty  stockProductProp = indexedPropertyRepository.findByNameAndIndex(stockPropName, index);
        if (stockProductProp == null) {
            stockProductProp = new EsIndexedProperty();
            stockProductProp.setName(stockPropName);
            stockProductProp.setType(EsPropertyType.KEYWORD);
            stockProductProp.setFtsQuery(false);
            stockProductProp.setFacet(true);
            stockProductProp.setIncludeInFacets(false);
            stockProductProp.setIndex(index);
            indexedPropertyRepository.save(stockProductProp);
        }

        final String variationOptionIdPropName = "productVariationOptionId";
        EsIndexedProperty  variationOptionIdProductProp = indexedPropertyRepository.findByNameAndIndex(variationOptionIdPropName, index);
        if (variationOptionIdProductProp == null) {
            variationOptionIdProductProp = new EsIndexedProperty();
            variationOptionIdProductProp.setName(variationOptionIdPropName);
            variationOptionIdProductProp.setType(EsPropertyType.KEYWORD);
            variationOptionIdProductProp.setFtsQuery(false);
            variationOptionIdProductProp.setFacet(true);
            variationOptionIdProductProp.setIncludeInFacets(false);
            variationOptionIdProductProp.setIndex(index);
            indexedPropertyRepository.save(variationOptionIdProductProp);
        }

        final String attributesPropName = "attributeProps";
        EsIndexedProperty attributesProductProp = indexedPropertyRepository.findByNameAndIndex(attributesPropName, index);
        if (attributesProductProp == null) {
            attributesProductProp = new EsIndexedProperty();
            attributesProductProp.setName(attributesPropName);
            attributesProductProp.setDisplayName(attributesPropName);
            attributesProductProp.setType(EsPropertyType.TEXT);
            attributesProductProp.setMultiFieldsType(EsPropertyType.KEYWORD);
            attributesProductProp.setFtsQuery(true);
            attributesProductProp.setFacet(true);
            attributesProductProp.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
            attributesProductProp.setPriority(100);
            attributesProductProp.setIndex(index);
            indexedPropertyRepository.save(attributesProductProp);
        }

        final String imagesCollectionPropName = "imagesCollection";
        EsIndexedProperty imagesCollectionProductProp = indexedPropertyRepository.findByNameAndIndex(imagesCollectionPropName, index);
        if (imagesCollectionProductProp == null) {
            imagesCollectionProductProp = new EsIndexedProperty();
            imagesCollectionProductProp.setName(imagesCollectionPropName);
            imagesCollectionProductProp.setType(EsPropertyType.TEXT);
            imagesCollectionProductProp.setMultiFieldsType(EsPropertyType.KEYWORD);
            imagesCollectionProductProp.setFtsQuery(false);
            imagesCollectionProductProp.setFacet(true);
            imagesCollectionProductProp.setIncludeInFacets(false);
            imagesCollectionProductProp.setIndex(index);
            indexedPropertyRepository.save(imagesCollectionProductProp);
        }

        final String parentSkuPropName = "parentSku";
        EsIndexedProperty parentSkuProp = indexedPropertyRepository.findByNameAndIndex(parentSkuPropName, index);
        if (parentSkuProp == null) {
            parentSkuProp = new EsIndexedProperty();
            parentSkuProp.setName(parentSkuPropName);
            parentSkuProp.setType(EsPropertyType.KEYWORD);
            parentSkuProp.setFtsQuery(false);
            parentSkuProp.setIndex(index);
            indexedPropertyRepository.save(parentSkuProp);
        }

//        String[] attributeLabelsProps = callApiGetDataAttributes();
//        List<EsIndexedProperty> esIndexedProperties = new ArrayList<>();
//        for (String attributeLabelsProp : attributeLabelsProps) {
//            final String nameAttributes = getFieldName(ATTRIBUTE_PREFIX, upperCaseValue(removeSpace(attributeLabelsProp)));
//            EsIndexedProperty esIndexedProperty = indexedPropertyRepository.findByNameAndIndex(nameAttributes, index);
//            if (esIndexedProperty == null) {
//                esIndexedProperty = new EsIndexedProperty();
//                esIndexedProperty.setName(nameAttributes);
//                esIndexedProperty.setType(EsPropertyType.KEYWORD);
//                esIndexedProperty.setFtsQuery(true);
//                esIndexedProperty.setFacet(true);
//                esIndexedProperty.setDisplayName(attributeLabelsProp);
//                esIndexedProperty.setIndex(index);
//                esIndexedProperty.setFacetType(EsIndexedPropertyFacetType.MULTISELECT_OR);
//                esIndexedProperties.add(esIndexedProperty);
//            }
//
//        }
//
//        indexedPropertyRepository.saveAll(esIndexedProperties);

    }

    private String[] callApiGetDataAttributes() {
        String getAttributesEndpoint = searchProperties.getProduct().getIndexEndpoint() + "/" + "attributes";
        return restTemplate.getForObject(getAttributesEndpoint, String[].class);
    }

    private EsAdvancedSearchConfig createAdvancedSearchConfig(final EsFacetSearchConfig facetSearchConfig) {
        // Create EsAdvancedSearchConfig
        EsAdvancedSearchConfig advancedSearchConfig = advancedSearchConfigRepository.findByFacetSearchConfig(facetSearchConfig);
        if (advancedSearchConfig == null) {
            advancedSearchConfig = new EsAdvancedSearchConfig();
            advancedSearchConfig.setDescription("Advanced search config");
            advancedSearchConfig.setBoostRulesMergeMode(EsBoostRulesMergeMode.ADD);
            advancedSearchConfig.setFacetSearchConfig(facetSearchConfig);
            advancedSearchConfigRepository.save(advancedSearchConfig);
        }
        return advancedSearchConfig;
    }

    private void createPromotedItems(final EsAdvancedSearchConfig advancedSearchConfig) {
        // Create EsPromotedItem
        final List<EsPromotedItem> promotedItems = promotedItemRepository.findByAdvancedSearchConfig(advancedSearchConfig);
        if (CollectionUtils.isEmpty(promotedItems)) {
            final long itemId = 31496L;
            final EsPromotedItem promotedItem = new EsPromotedItem();
            promotedItem.setItemId(Long.valueOf(itemId));
            promotedItem.setAdvancedSearchConfig(advancedSearchConfig);
            promotedItemRepository.save(promotedItem);
        }
    }

    private void createBoostRules(final EsAdvancedSearchConfig advancedSearchConfig) {
        // Create EsBoostRule
        final List<EsBoostRule> boostRules = boostRuleRepository.findByAdvancedSearchConfig(advancedSearchConfig);
        if (CollectionUtils.isEmpty(boostRules)) {
            final float boost = 10f;
            final EsBoostRule boostRule = new EsBoostRule();
            boostRule.setIndexProperty("category.name");
            boostRule.setOperator(EsBoostOperator.EQUAL);
            boostRule.setValue("Apple");
            boostRule.setBoost(boost);
            boostRule.setAdvancedSearchConfig(advancedSearchConfig);
            boostRuleRepository.save(boostRule);
        }
    }

    private void createStopWords(final EsFacetSearchConfig facetSearchConfig) {
        final List<EsStopWord> stopWords = stopWordRepository.findByFacetSearchConfig(facetSearchConfig);
        if (CollectionUtils.isEmpty(stopWords)) {
            Arrays.asList("a", "an", "the", "some", "any", "testing").forEach(stopWord -> {
                final EsStopWord valueStopWord = new EsStopWord();
                valueStopWord.setWord(stopWord);
                valueStopWord.setFacetSearchConfig(facetSearchConfig);
                stopWordRepository.save(valueStopWord);
            });
        }
    }

    private void createSynonyms(final EsFacetSearchConfig facetSearchConfig) {
        final List<EsSynonymConfig> synonymConfigs = synonymConfigRepository.findByFacetSearchConfig(facetSearchConfig);
        if (CollectionUtils.isEmpty(synonymConfigs)) {
            Arrays.asList(new String[]{"shoe", "sneaker"}, new String[]{"sport", "adidas"})
                    .forEach(synonym -> {
                        final EsSynonymConfig valueSynonym = new EsSynonymConfig();
                        valueSynonym.setSynonymFrom(synonym[0]);
                        valueSynonym.setSynonymTo(synonym[1]);
                        valueSynonym.setFacetSearchConfig(facetSearchConfig);
                        synonymConfigRepository.save(valueSynonym);
                    });
        }
    }

    private String getFieldName(final String fieldPrefix, final String groupId) {
        if (StringUtils.isBlank(groupId)) {
            return fieldPrefix;
        }

        return StringUtils.join(fieldPrefix, UNDER_SCORE, groupId);
    }

    private String upperCaseValue(final String value) {
        return StringUtils.upperCase(StringUtils.trim(value));
    }

    private String removeSpace(final String value) {
        return value.replaceAll("\\s+", "");
    }
}
