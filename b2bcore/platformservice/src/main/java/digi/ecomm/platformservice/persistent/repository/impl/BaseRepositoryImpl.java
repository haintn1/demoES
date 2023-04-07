package digi.ecomm.platformservice.persistent.repository.impl;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

/**
 * An intermediate implementation for adding custom behavior to all repositories.
 *
 * @param <T>
 * @param <ID>
 */
public class BaseRepositoryImpl<T extends AbstractEntity, ID extends Long> extends SimpleJpaRepository<T, ID> //NOSONAR
        implements BaseRepository<T, ID> { //NOSONAR

    public BaseRepositoryImpl(final Class<T> domainClass, final EntityManager entityManager) {
        super(domainClass, entityManager);
    }

    public BaseRepositoryImpl(final JpaEntityInformation<T, ?> entityInformation, final EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}
