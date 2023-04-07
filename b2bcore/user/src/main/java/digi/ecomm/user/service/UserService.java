package digi.ecomm.user.service;

import digi.ecomm.entity.user.User;
import digi.ecomm.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        User user = userRepository.findByUid(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(user.getUid(), user.getEncodedPassword(),
                true, true, true, true, getAuthorities());
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Load user by id.
     *
     * @param id
     * @return
     */
    public UserDetails loadUserById(final Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (!userOpt.isPresent()) {
            throw new UsernameNotFoundException(String.valueOf(id));
        }

        User user = userOpt.get();
        return new org.springframework.security.core.userdetails.User(user.getUid(), user.getEncodedPassword(),
                true, true, true, true, getAuthorities());
    }

}
