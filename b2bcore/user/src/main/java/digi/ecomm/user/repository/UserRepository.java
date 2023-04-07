package digi.ecomm.user.repository;

import digi.ecomm.entity.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PrincipalRepository<User> {

    /**
     * Find <code>User</code> by uid.
     *
     * @param uid
     * @return
     */
    User findByUid(String uid);
}
