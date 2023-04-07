package digi.ecomm.user.repository;

import digi.ecomm.entity.user.Principal;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrincipalRepository<T extends Principal> extends BaseRepository<T, Long> {

    /**
     * Find <code>User</code> by uid.
     *
     * @param uid
     * @return
     */
    T findByUid(String uid);
}
