package digi.ecomm.searchstandardapi.provider.populator;

import digi.ecomm.commercesearch.index.provider.data.IndexedProduct;
import digi.ecomm.datatransferobject.product.response.B2bProduct;
import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Populator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class IndexedProductPopulator implements Populator<B2bProduct, IndexedProduct> {
    private static final String UNDER_SCORE = "_";
    private static final String PRICE_PREFIX = "price";
    private static final String MIN_PRICE_PREFIX = "minPrice";
    private static final String MAX_PRICE_PREFIX = "maxPrice";
    private static final String VARIATION_PREFIX = "variation";
    private static final String ATTRIBUTE_PREFIX = "attribute";

    @Override
    public void populate(final B2bProduct source, final IndexedProduct target) throws ConversionException {
        target.setId(source.getId());
        target.setUuid(source.getUuid());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setImage(source.getImage());
        target.setSku(source.getSku());
        target.setType(source.getType());
        target.setCategoryNames(source.getCategoryNames());
        target.setSuggestName(source.getSuggestName());
        target.setDiscount(source.getDiscount());
        target.setBaseProduct(source.isBaseProduct());
        target.setGroupNames(source.getGroupNames());
        target.setDefaultUOM(source.getDefaultUOM());
        target.setBranchId(source.getBranchId());
        target.setStock(source.getStock());
        target.setMinPackQty(source.getMinPackQty());
        target.setUoms(source.getUoms());
        target.setProductVariationOptionId(source.getProductVariationOptionId());
        target.setImagesCollection(source.getImagesCollection());
        target.setParentSku(source.getParentSku());
        target.setColour(source.getColour());
        target.setPrice(source.getPrice());
        final Map<String, Object> dynamicFieldMap = new LinkedHashMap<>();
        if (CollectionUtils.isNotEmpty(source.getPrices())) {
            source.getPrices().forEach(price -> {
                if (price.getPriceValue() != null) {
                    dynamicFieldMap.put(getFieldName(PRICE_PREFIX, price.getGroupId()), price.getPriceValue());
                } else {
                    dynamicFieldMap.put(getFieldName(PRICE_PREFIX, price.getGroupId()), price.getMinPrice());
                    dynamicFieldMap.put(getFieldName(MIN_PRICE_PREFIX, price.getGroupId()), price.getMinPrice());
                    dynamicFieldMap.put(getFieldName(MAX_PRICE_PREFIX, price.getGroupId()), price.getMaxPrice());
                }
            });
        }

        if (CollectionUtils.isNotEmpty(source.getVariations())) {
            source.getVariations().stream()
                    .filter(variation -> variation.getVariationName() != null && variation.getOptionValue() != null)
                    .forEach(variation -> dynamicFieldMap.put(
                            getFieldName(VARIATION_PREFIX, upperCaseValue(removeSpace(variation.getVariationName()))),
                            upperCaseValue(variation.getOptionValue())));
        }

        if (CollectionUtils.isNotEmpty(source.getAttributeProps())) {
            source.getAttributeProps().stream()
                    .filter(attributeValueProp -> StringUtils.isNotEmpty(attributeValueProp.getValue())
                            && StringUtils.isNotEmpty(attributeValueProp.getLabel()))
                    .forEach(attributeValueProp -> dynamicFieldMap.put(
                            getFieldName(ATTRIBUTE_PREFIX, upperCaseValue(removeSpace(attributeValueProp.getLabel()))),
                            upperCaseValue(attributeValueProp.getValue())));
        }

        target.setDynamicFields(dynamicFieldMap);
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
