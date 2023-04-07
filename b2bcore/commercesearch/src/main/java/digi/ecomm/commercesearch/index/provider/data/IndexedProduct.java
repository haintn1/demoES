package digi.ecomm.commercesearch.index.provider.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import digi.ecomm.datatransferobject.product.response.ItemUOM;
import digi.ecomm.datatransferobject.search.AbstractIndexedSourceItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class IndexedProduct extends AbstractIndexedSourceItem {
    @JsonProperty(value = "uuid")
    private String uuid;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "image")
    private String image;

    @JsonProperty(value = "sku")
    private String sku;

    @JsonProperty(value = "type")
    private int type;

    @JsonProperty(value = "categoryNames")
    private List<String> categoryNames;

    @JsonProperty(value = "suggestName")
    private String suggestName;

    @JsonProperty(value = "discount")
    private Integer discount;

    @JsonProperty(value = "isBaseProduct")
    private boolean isBaseProduct;

    @JsonProperty(value = "groupNames")
    private List<String> groupNames;

    @Column(name = "size")
    private String size;

    @Column(name = "ecommerceDescription")
    @Lob
    private String eCommerceDescription;

    @Column(name = "configurable")
    private Boolean configurable;

    @Column(name = "displayComplementaryItems")
    private Boolean displayComplementaryItems;

    @Column(name = "stock")
    private Boolean stock;

    @Column(name = "discontinued")
    private String discontinued;

    @Column(name = "allowRMmainPv")
    private Boolean allowRMAinPV;

    @Column(name = "stockingUOM")
    private String stockingUOM;

    @Column(name = "minPackQty")
    private Float minPackQty;

    @Column(name = "minPackUOM")
    private String minPackUOM;

    @Column(name = "branchId")
    private String branchId;

    @Column(name = "periodType")
    private Integer periodType;

    @Column(name = "extendedDescription")
    @Lob
    private String extendedDescription;

    @Column(name = "productVariationId")
    private String productVariationId;

    @Column(name = "itemCode")
    private String itemCode;

    @Column(name = "productUuidAgility")
    private String productUuidAgility;

    @Column(name = "itemType")
    private String itemType;

    @Column(name = "productGroupMajorGuid")
    private String productGroupMajorGuid;

    @Column(name = "productGroupMinorGuid")
    private String productGroupMinorGuid;

    @Column(name = "uom")
    private String uom;

    @Column(name = "defaultUOM")
    private String defaultUOM;

    @JsonProperty(value = "uoms")
    private List<ItemUOM> uoms;

    @JsonProperty(value = "productVariationOptionId")
    private String productVariationOptionId;

    @JsonProperty(value = "attributeProps")
    private List<String> attributeProps;

    @JsonProperty("imagesCollection")
    private List<String> imagesCollection;

    @JsonProperty(value = "parentSku")
    private String parentSku;

    @JsonProperty(value = "colour")
    private String colour;

    @JsonProperty(value = "price")
    private Double price;
}
