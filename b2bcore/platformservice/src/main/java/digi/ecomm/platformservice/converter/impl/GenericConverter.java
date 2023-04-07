package digi.ecomm.platformservice.converter.impl;


import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Converter;
import digi.ecomm.platformservice.converter.Populator;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GenericConverter<SOURCE, TARGET> implements Converter<SOURCE, TARGET>, InitializingBean, BeanNameAware { //NOSONAR
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericConverter.class);

    private Class<TARGET> targetClass;
    private List<digi.ecomm.platformservice.converter.Populator<SOURCE, TARGET>> populators;
    private String beanName;

    @Override
    public TARGET convert(final SOURCE source) throws ConversionException {
        final TARGET target = createFromClass();
        if (CollectionUtils.isNotEmpty(populators)) {
            for (final digi.ecomm.platformservice.converter.Populator<SOURCE, TARGET> populator : populators) {
                if (populator != null) {
                    populator.populate(source, target);
                }
            }
        }
        return target;
    }

    /**
     * Create instance from clazz.
     *
     * @return
     */
    protected TARGET createFromClass() {
        try {
            return targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ConversionException(e.getMessage(), e);
        }
    }

    @Override
    public void setBeanName(final String name) {
        this.beanName = name;
    }

    /**
     * Ensures that either a class has been set or createTarget() has been overridden.
     */
    @Override
    public void afterPropertiesSet() {
        if (targetClass == null) {
            throw new IllegalStateException("Converter '" + beanName + "' doesn't have a targetClass property!");
        }
    }

    /**
     * Remove duplicate populators.
     */
    @PostConstruct
    public void removeDuplicatedPopulators() {
        if (CollectionUtils.isNotEmpty(populators)) {
            final Set<digi.ecomm.platformservice.converter.Populator<SOURCE, TARGET>> distinctPopulators = new LinkedHashSet<>();

            for (final digi.ecomm.platformservice.converter.Populator<SOURCE, TARGET> populator : populators) {
                if (!distinctPopulators.add(populator)) {
                    LOGGER.warn("Duplicate populator entry [{}] found for converter {}! The duplication has been removed.",
                            populator.getClass().getName(), beanName);
                }
            }
            populators = new ArrayList<>(distinctPopulators);
        } else {
            LOGGER.warn("Empty populators list found for converter {}!", beanName);
        }
    }

    public void setTargetClass(final Class<TARGET> targetClass) {
        this.targetClass = targetClass;
    }

    public void setPopulators(final List<Populator<SOURCE, TARGET>> populators) {
        this.populators = populators;
    }
}
