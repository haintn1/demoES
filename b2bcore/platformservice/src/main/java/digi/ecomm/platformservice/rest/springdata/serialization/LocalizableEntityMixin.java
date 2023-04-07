package digi.ecomm.platformservice.rest.springdata.serialization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import digi.ecomm.entity.localized.LocalizableAugmentation;
import digi.ecomm.entity.localized.LocalizableEntity;

import java.util.Map;

public interface LocalizableEntityMixin<AUG extends LocalizableAugmentation> extends LocalizableEntity<AUG> { //NOSONAR

    @JsonIgnore
    @Override
    Map<String, AUG> getTranslations();
}
