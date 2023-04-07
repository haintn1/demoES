package digi.ecomm.platformservice.rest.springdata.hal;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.hateoas.EntityModel;

public class PersistentEntityResourceWrapper<T> extends EntityModel<T> {

    @JsonProperty("data")
    private final PersistentEntityResource persistentEntityResource;

    public PersistentEntityResourceWrapper(final PersistentEntityResource persistentEntityResource) {
        this.persistentEntityResource = persistentEntityResource;
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
