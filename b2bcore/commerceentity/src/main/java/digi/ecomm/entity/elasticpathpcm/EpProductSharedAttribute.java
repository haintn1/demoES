package digi.ecomm.entity.elasticpathpcm;

public interface EpProductSharedAttribute {

    /**
     * JPA embedding contains common attributes of EP products.
     *
     * @return
     */
    EpProductSharedAttributeEmbedding getSharedAttributes();

    /**
     * Get product commodity type.
     *
     * @return
     */
    default String getCommodityType() {
        return getSharedAttributes().getCommodityType();
    }

    /**
     * Set product commodity type.
     *
     * @param commodityType
     */
    default void setCommodityType(String commodityType) {
        getSharedAttributes().setCommodityType(commodityType);
    }
}
