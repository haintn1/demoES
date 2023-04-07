package digi.ecomm.datatransferobject.product.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class B2bProduct {

    @JsonProperty("id")
    private Long id;

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

    @JsonProperty(value = "price")
    private Double price;

    @JsonProperty(value = "type")
    private int type;

    @JsonProperty(value = "categoryNames")
    private List<String> categoryNames;

    @JsonProperty(value = "prices")
    private List<PriceProps> prices;

    @JsonProperty(value = "suggestName")
    private String suggestName;

    @JsonProperty(value = "discount")
    private Integer discount;

    @JsonProperty(value = "isBaseProduct")
    private boolean isBaseProduct;

    @JsonProperty(value = "variations")
    private List<VariationProps> variations;

    @JsonProperty(value = "groupNames")
    private List<String> groupNames;

    @JsonProperty(value = "defaultUOM")
    private String defaultUOM;

    @JsonProperty(value = "minPackQty")
    private Float minPackQty;

    @JsonProperty(value = "branchId")
    private String branchId;

    @JsonProperty(value = "stock")
    private Boolean stock;

    @JsonProperty(value = "uoms")
    private List<ItemUOM> uoms;

    @JsonProperty(value = "productVariationOptionId")
    private String productVariationOptionId;

    @JsonProperty(value = "attributeProps")
    private List<AttributeValueProp> attributeProps;

    @JsonProperty("imagesCollection")
    private List<String> imagesCollection;

    @JsonProperty(value = "parentSku")
    private String parentSku;

    @JsonProperty(value = "colour")
    private String colour;
}
