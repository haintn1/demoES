package digi.ecomm.platformservice.persistent.repository;

import digi.ecomm.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<T extends AbstractEntity, ID extends Long> extends JpaRepository<T, ID>, //NOSONAR
        JpaSpecificationExecutor<T> {

    String REST_COLLECTION_RESOURCE_REL = "data";

}
