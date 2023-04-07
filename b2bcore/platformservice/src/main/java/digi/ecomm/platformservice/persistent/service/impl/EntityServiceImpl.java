package digi.ecomm.platformservice.persistent.service.impl;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.platformservice.persistent.service.EntityService;
import digi.ecomm.platformservice.util.ServicesUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class EntityServiceImpl implements EntityService {

    private final EntityManager entityManager;
    private final TransactionTemplate txTemplate;

    public EntityServiceImpl(final EntityManager entityManager, final PlatformTransactionManager txManager) {
        this.entityManager = entityManager;
        this.txTemplate = new TransactionTemplate(txManager);
    }

    @Transactional
    @Override
    public <T extends AbstractEntity> void delete(final T entity) {
        ServicesUtils.validateParameterNotNullStandardMessage("entity", entity); //NOSONAR
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public <T extends AbstractEntity> void deleteAll(final Iterable<? extends T> entities) {
        ServicesUtils.validateParameterNotNullStandardMessage("entities", entities);
        entities.forEach(this::delete);
    }

    @Transactional(readOnly = true)
    @Override
    public <T extends AbstractEntity, ID extends Long> T getById(final Class<T> entityClazz, final ID id) { //NOSONAR
        ServicesUtils.validateParameterNotNullStandardMessage("entityClazz", entityClazz);
        ServicesUtils.validateParameterNotNullStandardMessage("id", id);
        return entityManager.find(entityClazz, id);
    }

    @Transactional(readOnly = true)
    @Override
    public <T extends AbstractEntity> boolean exists(final T entity) {
        ServicesUtils.validateParameterNotNullStandardMessage("entity", entity); //NOSONAR
        return Objects.nonNull(entity.getId()) && Objects.nonNull(entityManager.getReference(entity.getClass(), entity.getId()));
    }

    @Transactional
    @Override
    public <T extends AbstractEntity> T save(final T entity) {
        ServicesUtils.validateParameterNotNullStandardMessage("entity", entity); //NOSONAR
        if (!exists(entity)) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }

    @Transactional
    @Override
    public <T extends AbstractEntity> void saveAndFlush(final T entity) {
        ServicesUtils.validateParameterNotNullStandardMessage("entity", entity); //NOSONAR
        save(entity);
        entityManager.flush();
    }

    @Transactional
    @Override
    public <T extends AbstractEntity> List<T> save(final Iterable<T> entities) {
        ServicesUtils.validateParameterNotNullStandardMessage("entities", entities);
        final List<T> result = new ArrayList<>();
        entities.forEach(entity -> result.add(save(entity)));
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public <T extends AbstractEntity> void refresh(final T entity) {
        ServicesUtils.validateParameterNotNullStandardMessage("entity", entity); //NOSONAR
        entityManager.refresh(entity);
    }

    @Transactional
    @Override
    public void flush() {
        entityManager.flush();
    }

    @Override
    public void executeInTransaction(final Consumer<TransactionStatus> consumer) {
        txTemplate.executeWithoutResult(consumer::accept);
    }

    @Override
    public <T extends AbstractEntity> T executeInTransaction(final Function<TransactionStatus, T> function) {
        return txTemplate.execute(function::apply);
    }
}
