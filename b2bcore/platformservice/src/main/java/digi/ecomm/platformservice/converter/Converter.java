package digi.ecomm.platformservice.converter;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * @param <SOURCE>
 * @param <TARGET>
 */
public interface Converter<SOURCE, TARGET> { //NOSONAR

    /**
     * Convert <code>SOURCE</code> to <code>TARGET</code>.
     *
     * @param source
     * @return
     * @throws ConversionException
     */
    TARGET convert(SOURCE source) throws ConversionException;

    /**
     * Convert all <code>SOURCE</code>s to <code>TARGET</code>s.
     *
     * @param sources
     * @return
     * @throws ConversionException
     */
    default List<TARGET> convertAll(Collection<? extends SOURCE> sources) throws ConversionException {
        if (CollectionUtils.isEmpty(sources)) {
            return Collections.emptyList();
        } else {
            final List<TARGET> targets = new ArrayList<>(sources.size());
            final Iterator<? extends SOURCE> iterator = sources.iterator();

            while (iterator.hasNext()) {
                final SOURCE source = iterator.next();
                targets.add(this.convert(source));
            }

            return targets;
        }
    }
}

