package dev.carv.spring.sec.user;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.UUID;

@Value
@Builder
@RequiredArgsConstructor
public class CustomerDetailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    UUID id;
    String username;
    String email;
    String password;
    Collection<? extends GrantedAuthority> authorities;
    boolean enabled;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

}
