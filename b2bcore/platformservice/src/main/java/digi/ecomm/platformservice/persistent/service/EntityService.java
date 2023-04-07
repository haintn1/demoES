package digi.ecomm.platformservice.persistent.service;

import digi.ecomm.entity.AbstractEntity;
import org.springframework.transaction.TransactionStatus;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface EntityService {

    /**
     * Delete <code>entity</code>.
     *
     * @param entity
     */
    <T extends AbstractEntity> void delete(T entity);

    /**
     * Delete <code>extends</code>.
     *
     * @param entities
     */
    <T extends AbstractEntity> void deleteAll(Iterable<? extends T> entities);

    /**
     * Get entity by <code>id</code>.
     *
     * @param entityClazz
     * @param id
     * @return
     */
    <T extends AbstractEntity, ID extends Long> T getById(Class<T> entityClazz, ID id); //NOSONAR

    /**
     * Check if entity with <code>id</code> exist.
     *
     * @param entity
     * @return
     */
    <T extends AbstractEntity> boolean exists(T entity);

    /**
     * Save <code>entity</code>.
     *
     * @param entity
     * @return
     */
    <T extends AbstractEntity> T save(T entity);

    /**
     * Save and flush <code>entity</code>.
     *
     * @param entity
     */
    <T extends AbstractEntity> void saveAndFlush(T entity);

    /**
     * Save <code>entities</code>.
     *
     * @param entities
     * @return
     */
    <T extends AbstractEntity> List<T> save(Iterable<T> entities);

    /**
     * Refresh the state of the instance from the database,
     * overwriting changes made to the entity, if any.
     *
     * @param entity entity instance
     */
    <T extends AbstractEntity> void refresh(T entity);

    /**
     * Flush.
     */
    void flush();

    /**
     * Execute {@code consumer} in transaction.
     *
     * @param consumer
     */
    void executeInTransaction(Consumer<TransactionStatus> consumer);

    /**
     * Execute {@code function} in transaction.
     *
     * @param function
     */
    <T extends AbstractEntity> T executeInTransaction(Function<TransactionStatus, T> function);
}
