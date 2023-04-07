package digi.ecomm.platformservice.rest.springdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.localized.LocalizableAugmentation;
import digi.ecomm.entity.localized.LocalizableEntity;
import digi.ecomm.entity.localized.LocalizedId;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.data.mapping.Association;
import org.springframework.data.mapping.PersistentProperty;
import org.springframework.data.mapping.SimpleAssociationHandler;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.rest.webmvc.json.DomainObjectReader;
import org.springframework.data.rest.webmvc.mapping.Associations;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EnhancedDomainObjectReader<AUG extends LocalizableAugmentation> extends DomainObjectReader { //NOSONAR

    private final PersistentEntities entities;
    private final Associations associationLinks;

    public EnhancedDomainObjectReader(final PersistentEntities entities, final Associations associationLinks) {
        super(entities, associationLinks);
        this.entities = entities;
        this.associationLinks = associationLinks;
        BeanUtilsBean.getInstance().getConvertUtils().register(false, true, 0);
    }

    @Override
    public <T> T readPut(final ObjectNode source, final T target, final ObjectMapper mapper) throws Exception {
        final T newObject = super.readPut(source, target, mapper);
        final AbstractEntity intermediate = mapper.readerFor(target.getClass()).readValue(source);
        if (newObject instanceof LocalizableEntity) {
            populatePutL10nData((LocalizableEntity<AUG>) intermediate, (LocalizableEntity<AUG>) newObject);
        }
        populatePutRelationship(intermediate, (AbstractEntity) newObject);
        return newObject;
    }

    private void populatePutRelationship(final AbstractEntity source, final AbstractEntity target) {
        final Class<? extends Object> type = target.getClass();
        entities.getPersistentEntity(type)
                .filter(it -> !it.isImmutable())
                .ifPresent(it -> it.doWithAssociations(new LinkedAssociationHandler(associationLinks, source, target)));
    }

    /**
     * {@link SimpleAssociationHandler} that handles linkable associations.
     */
    private static final class LinkedAssociationHandler implements SimpleAssociationHandler {

        private final Associations associations;
        private final AbstractEntity source;
        private final AbstractEntity target;

        private LinkedAssociationHandler(final Associations associations, final AbstractEntity source, final AbstractEntity target) {
            Assert.notNull(associations, "Associations must not be null!");

            this.associations = associations;
            this.source = source;
            this.target = target;
        }

        @Override
        public void doWithAssociation(final Association<? extends PersistentProperty<?>> association) {

            if (associations.isLinkableAssociation(association)) {
                final PersistentProperty<?> persistentProperty = association.getInverse();
                final Method getter;
                final Method setter;
                if (!persistentProperty.isMap() && persistentProperty.isWritable()
                        && (setter = persistentProperty.getSetter()) != null
                        && (getter = persistentProperty.getGetter()) != null) {
                    try {
                        setter.invoke(target, getter.invoke(source));
                    } catch (Exception e) {
                        throw new AssociationSaveException(e.getMessage(), e);
                    }
                }
            }
        }
    }

    private void populatePutL10nData(final LocalizableEntity<AUG> intermediate, final LocalizableEntity<AUG> newObject)
            throws IllegalAccessException, InvocationTargetException {

        for (final String lang : newObject.getTranslations().keySet()) {
            if (!intermediate.getTranslations().containsKey(lang)) {
                intermediate.getTranslations().put(lang, intermediate.translationSupplier().get());
            }
        }

        for (final String lang : intermediate.getTranslations().keySet()) {
            final AUG existingTranslation = newObject.getTranslations().get(lang);
            if (existingTranslation != null) {
                BeanUtils.copyProperties(existingTranslation, intermediate.getTranslations().get(lang));
            } else {
                final AUG newTranslation = newObject.translationSupplier().get();
                BeanUtils.copyProperties(newTranslation, intermediate.getTranslations().get(lang));
                newTranslation.setEntity(newObject);
                newTranslation.setLocalizedId(new LocalizedId(lang));
                newObject.getTranslations().put(lang, newTranslation);
            }
        }
    }
}
