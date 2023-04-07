package digi.ecomm.datatransferobject.product.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ItemUOM {
	@JsonProperty("uom")
	private String uom;

	@JsonProperty("minPackQty")
	private int minPackQty;
}
