package eu.ensup.user.service;

import eu.ensup.user.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    User save(User user);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}