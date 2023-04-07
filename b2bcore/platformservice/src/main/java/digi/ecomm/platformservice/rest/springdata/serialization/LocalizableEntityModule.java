package digi.ecomm.platformservice.rest.springdata.serialization;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import digi.ecomm.entity.localized.LocalizableEntity;

public class LocalizableEntityModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public LocalizableEntityModule() {
        super("json-localizable-entity-module",
                new Version(1, 0, 0, null, "digi.ecomm", "platformservice"));
        setMixInAnnotation(LocalizableEntity.class, LocalizableEntityMixin.class);
    }
}
