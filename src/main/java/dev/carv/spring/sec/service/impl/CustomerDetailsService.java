package dev.carv.spring.sec.service.impl;

import dev.carv.spring.sec.repository.CustomerRepository;
import dev.carv.spring.sec.user.CustomerDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository repository;

    /**
     * Load a user by username (or email).
     *
     * @param username the username (or email) to search for
     * @return the user with the given username
     * @throws UsernameNotFoundException if no user is found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var customer = repository.findByUsername(username)
            .or(() -> repository.findByEmail(username))
            .orElseThrow(() -> new UsernameNotFoundException("Customer not found with email: %s".formatted(username)));

        return CustomerDetailsImpl.builder()
            .username(customer.getUsername())
            .email(customer.getEmail())
            .password(customer.getPassword())
            .authorities(List.of(new SimpleGrantedAuthority(customer.getRole())))
            .enabled(customer.isEnabled())
            .build();
    }

}
