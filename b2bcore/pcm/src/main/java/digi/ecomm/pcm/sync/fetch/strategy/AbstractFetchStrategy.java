package digi.ecomm.pcm.sync.fetch.strategy;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.sync.SynchronizableEntity;
import digi.ecomm.platformservice.persistent.service.EntityService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractFetchStrategy<T extends SynchronizableEntity> {

    @Resource
    private EntityService entityService;

    /**
     * Process.
     *
     * @param fetchedEntities
     * @return
     */
    protected final List<T> processInternal(final List<T> fetchedEntities) {
        final List<T> existingEntities = getEntitiesByExternalIds(
                fetchedEntities.stream().map(T::getExternalId).collect(Collectors.toList()));
        final List<T> mergedEntities = mergeEntities(fetchedEntities, existingEntities);
        getEntityService().save(mergedEntities.stream().map(AbstractEntity.class::cast).collect(Collectors.toList()));
        return mergedEntities;
    }

    private List<T> mergeEntities(final List<T> variations, final List<T> existingVariations) {
        final Set<T> mergedVariations = new HashSet<>();
        final Map<String, T> existingMap = existingVariations.stream()
                .collect(Collectors.toMap(T::getExternalId, Function.identity()));
        final Map<Boolean, List<T>> partitionedMap = variations.stream()
                .collect(Collectors.partitioningBy(variation -> existingMap.containsKey(variation.getExternalId())));
        partitionedMap.get(Boolean.TRUE)
                .forEach(source -> {
                    final T existingEntity = existingMap.get(source.getExternalId());
                    copyProperties(source, existingEntity);
                    mergedVariations.add(existingEntity);
                });
        mergedVariations.addAll(partitionedMap.get(Boolean.FALSE));

        return mergedVariations.stream().collect(Collectors.toList());
    }

    protected EntityService getEntityService() {
        return entityService;
    }

    /**
     * Get existing entities by external Id.
     *
     * @param externalIds
     * @return
     */
    protected abstract List<T> getEntitiesByExternalIds(List<String> externalIds);

    /**
     * Copy properties to the existing entity.
     *
     * @param source
     * @param target
     */
    protected abstract void copyProperties(T source, T target);
}
