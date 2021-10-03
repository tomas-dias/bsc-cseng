package org.trabalho.pratico.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @PostConstruct
    public void init() {
        String password = "admin";
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        User user = repository.findOneByUsername("admin");
        if(user == null) {
            repository.save(new User("Senhor", "Docente", "admin@uevora.pt", "admin", encodedPassword, "ROLE_ADMIN"));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = repository.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }

}
