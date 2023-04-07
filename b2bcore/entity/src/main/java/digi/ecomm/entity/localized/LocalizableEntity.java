package digi.ecomm.entity.localized;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public interface LocalizableEntity<AUG extends LocalizableAugmentation> { //NOSONAR

    Logger LOGGER = LoggerFactory.getLogger(LocalizableEntity.class);
    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Get map of translations.
     *
     * @return
     */
    Map<String, AUG> getTranslations();

    /**
     * Translation supplier.
     *
     * @return
     */
    Supplier<AUG> translationSupplier();

    /**
     * Get localized value.
     *
     * @param valueGetter
     * @return
     */
    default <VAL extends Object> VAL getLocalizedValue(final Function<AUG, VAL> valueGetter) { //NOSONAR
        return getLocalizedValue(null, valueGetter);
    }

    /**
     * Get localized value.
     *
     * @param locale
     * @param valueGetter
     * @return
     */
    default <VAL extends Object> VAL getLocalizedValue(final Locale locale, final Function<AUG, VAL> valueGetter) { //NOSONAR
        Preconditions.checkArgument(getTranslations() != null, "translations cannot be null");
        Preconditions.checkArgument(valueGetter != null, "valueGetter cannot be null");

        final String language;
        if (locale == null) {
            language = currentLanguage();
        } else {
            language = StringUtils.defaultIfBlank(locale.getLanguage(), currentLanguage());
        }

        final AUG translation = getTranslations().get(language);
        if (translation != null) {
            return valueGetter.apply(translation);
        }
        return null;
    }

    /**
     * Set localized value.
     *
     * @param value
     * @param valueSetter
     */
    default <VAL extends Object> void setLocalizedValue(final VAL value, final BiConsumer<AUG, VAL> valueSetter) { //NOSONAR

        setLocalizedValue(null, value, valueSetter);
    }

    /**
     * Set localized value.
     *
     * @param locale
     * @param value
     * @param valueSetter
     */
    default <VAL extends Object> void setLocalizedValue(final Locale locale, final VAL value, //NOSONAR
                                                        final BiConsumer<AUG, VAL> valueSetter) { //NOSONAR

        Preconditions.checkArgument(getTranslations() != null, "translations cannot be null");
        Preconditions.checkArgument(valueSetter != null, "valueSetter cannot be null");

        final String language;
        if (locale == null) {
            language = currentLanguage();
        } else {
            language = StringUtils.defaultIfBlank(locale.getLanguage(), currentLanguage());
        }

        AUG translation = getTranslations().get(language);
        if (translation != null) {
            valueSetter.accept(translation, value);
        } else {
            translation = translationSupplier().get();
            translation.setLocalizedId(new LocalizedId(language));
            translation.setEntity(this);
            valueSetter.accept(translation, value);
            getTranslations().put(language, translation);
        }
    }

    /**
     * Check if the {@code attributeName} is localized or not.
     *
     * @param attributeName
     * @return
     */
    default boolean isLocalizedAttribute(final String attributeName) {
        Preconditions.checkArgument(getTranslations() != null, "translations cannot be null");
        Preconditions.checkArgument(attributeName != null, "attributeName cannot be null");
        final Class<AUG> translationType =
                (Class<AUG>) ((ParameterizedType) getTranslations().getClass().getGenericSuperclass())
                        .getActualTypeArguments()[1];
        return FieldUtils.getField(translationType, attributeName, true) != null;
    }

    /**
     * Get current language from request context.
     *
     * @return
     */
    default String currentLanguage() {
        return StringUtils.defaultIfBlank(LocaleContextHolder.getLocale().getLanguage(), Locale.ENGLISH.getLanguage());
    }

    /**
     * Convert L10N data from request payload.
     *
     * @param propertyName
     * @param valueMap
     */
    @JsonAnySetter
    default void convertInboundTranslations(final String propertyName, final Map<String, String> valueMap) {
        valueMap.forEach((lang, value) -> {
            final Locale resolveLocale;
            if ((resolveLocale = org.springframework.util.StringUtils.parseLocale(lang)) == null
                    || !LocaleUtils.isAvailableLocale(resolveLocale)) {
                throw new IllegalArgumentException(String.format("Not a valid language code of [%s]", lang));
            }
            final AUG newAug = translationSupplier().get();
            final Map<Object, Object> augMap = OBJECT_MAPPER.convertValue(newAug, Map.class);
            augMap.put(propertyName, value);
            final AUG aug = (AUG) OBJECT_MAPPER.convertValue(augMap, newAug.getClass());
            aug.setEntity(this);
            aug.setLocalizedId(new LocalizedId(lang));
            getTranslations().put(lang, aug);
        });
    }

    /**
     * Convert L10N data to response format.
     *
     * @return
     */
    @JsonAnyGetter
    default Map<String, Object> convertOutboundTranslations() {
        final Map<String, Map<String, String>> translationMap = OBJECT_MAPPER.convertValue(getTranslations(), Map.class);
        final List<TranslationItem> translationItems = new ArrayList<>();
        translationMap.forEach((lang, tran) ->
                tran.forEach((propertyName, value) -> translationItems.add(new TranslationItem(lang, propertyName, value))));

        final Map<String, Object> result = new HashMap<>();
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        final boolean l10nEnabled = requestAttributes != null && Boolean.parseBoolean(String.valueOf(
                requestAttributes.getAttribute("l10nModeEnabled", RequestAttributes.SCOPE_REQUEST)));
        translationItems.stream()
                .collect(Collectors.groupingBy(TranslationItem::getPropertyName))
                .forEach((propertyName, tranItems) -> {
                    if (l10nEnabled) {
                        if (CollectionUtils.isNotEmpty(tranItems)) {
                            // Return data along with all language keys
                            final Map<String, String> tranItemMap = new TreeMap<>();
                            for (TranslationItem item : tranItems) {
                                tranItemMap.put(item.getLanguage(), item.getValue());
                            }
                            result.put(propertyName, tranItemMap);
                        } else {
                            // No data return
                            result.put(propertyName, null);
                        }

                    } else {
                        convertNonL10nMode(propertyName, tranItems, result);
                    }
                });

        return result;
    }

    /**
     * Handle non L10N mode.
     *
     * @param propertyName
     * @param tranItems
     * @param result
     */
    default void convertNonL10nMode(final String propertyName, final List<TranslationItem> tranItems,
                                    final Map<String, Object> result) {
        if (CollectionUtils.isNotEmpty(tranItems)) {
            // Find the matching translation, return data without a language key
            final Optional<TranslationItem> matching = tranItems.stream()
                    .filter(item -> StringUtils.equals(currentLanguage(), item.getLanguage()))
                    .findFirst();
            if (matching.isPresent()) {
                result.put(propertyName, matching.get().getValue());
            } else {
                result.put(propertyName, null);
            }
        } else {
            // No data return
            result.put(propertyName, null);
        }
    }

    class TranslationItem implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String language;
        private final String propertyName;
        private final String value;

        public TranslationItem(final String language, final String propertyName, final String value) {
            Preconditions.checkArgument(language != null);
            Preconditions.checkArgument(propertyName != null);

            this.language = language;
            this.propertyName = propertyName;
            this.value = value;
        }

        public String getLanguage() {
            return language;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public String getValue() {
            return value;
        }
    }
}
