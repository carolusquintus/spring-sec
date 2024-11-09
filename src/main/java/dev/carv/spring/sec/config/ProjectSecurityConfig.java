package dev.carv.spring.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
            .requestMatchers("/account", "/balance", "/card", "/loan").authenticated()
            .requestMatchers("/contact", "/notice", "/error").permitAll());
//        http.formLogin(FormLoginConfigurer::disable);
//        http.httpBasic(HttpBasicConfigurer::disable);
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
            User.builder()
                .username("user")
                .password("{bcrypt}$2a$12$LmkbFfLC1cDB615kJFdC3ObMKHi5u1S9/4eODBi1sMyv.XHKmr7ku")
                .authorities("READ")
                .build(),
            User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$J37YWF53955k9D98RSHL8.a9HUJi.a293kYAOH1aTcWYry7CUIWXO")
                .authorities("ADMIN")
                .build());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

}
