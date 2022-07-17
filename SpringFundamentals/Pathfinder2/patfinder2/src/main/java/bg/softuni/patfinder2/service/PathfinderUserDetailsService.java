package bg.softuni.patfinder2.service;

import bg.softuni.patfinder2.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

public class PathfinderUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public PathfinderUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .map(u -> new User(
                        u.getUsername(),
                        u.getPassword(),
                        u.getRoles()
                                .stream().map(
                                        r -> new SimpleGrantedAuthority("ROLE_" + r.getName().name()))
                                .collect(Collectors.toList()))
                )
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found"));
    }
}
