package digi.ecomm.platformservice.rest.springdata.hal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.hateoas.server.core.EmbeddedWrapper;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public class CollectionSerializer extends ContainerSerializer<Collection<?>> implements ContextualSerializer {

    private static final long serialVersionUID = 8030706944344625390L;

    private final transient BeanProperty property;

    public CollectionSerializer(final @Nullable BeanProperty property) {
        super(TypeFactory.defaultInstance().constructType(Collection.class));
        this.property = property;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(final Collection<?> value, final JsonGenerator jgen, final SerializerProvider provider) throws IOException {
        if (CollectionUtils.size(value) == 1) {
            final Object element = value.iterator().next();
            if (element instanceof EmbeddedWrapper) {
                final EmbeddedWrapper embeddedWrapper = (EmbeddedWrapper) element;
                if (embeddedWrapper.isCollectionValue() && CollectionUtils.isEmpty((Collection) embeddedWrapper.getValue())) {
                    provider.findValueSerializer(Collection.class, property).serialize(Collections.emptyList(), jgen, provider);
                    return;
                }
            }
        }
        provider.findValueSerializer(Collection.class, property).serialize(value, jgen, provider);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonSerializer<?> createContextual(final SerializerProvider prov, final BeanProperty property) {
        return new CollectionSerializer(property);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public JavaType getContentType() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public JsonSerializer<?> getContentSerializer() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty(final SerializerProvider provider, final Collection<?> value) {
        return value.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasSingleElement(final Collection<?> value) {
        return value.size() == 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    protected ContainerSerializer<?> _withValueTypeSerializer(final TypeSerializer vts) {
        return null;
    }
}
