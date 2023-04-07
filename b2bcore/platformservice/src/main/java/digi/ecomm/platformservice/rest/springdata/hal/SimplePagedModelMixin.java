package digi.ecomm.platformservice.rest.springdata.hal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;

import java.util.Collection;

@JsonPropertyOrder({"content", "links"})
public abstract class SimplePagedModelMixin<T> extends SimplePagedModel<T> {

    @Override
    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonSerialize(using = CollectionSerializer.class)
    @JsonDeserialize(using = Jackson2HalModule.HalResourcesDeserializer.class)
    public abstract Collection<T> getContent();
}