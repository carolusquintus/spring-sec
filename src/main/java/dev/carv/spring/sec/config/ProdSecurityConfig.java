package dev.carv.spring.sec.config;

import dev.carv.spring.sec.exception.handler.CustomAccessDeniedHandler;
import dev.carv.spring.sec.exception.handler.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
@Profile("prod")
public class ProdSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement(session -> session.invalidSessionUrl("/invalid-session"))
            .requiresChannel(channel -> channel.anyRequest().requiresSecure())
            .csrf(AbstractHttpConfigurer::disable)
            .cors(CorsConfig::getHttpSecurityCorsConfigurer)
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/account", "/balance", "/card", "/loan", "/customer/info").authenticated()
                .requestMatchers("/contact", "/notice", "/error", "/customer/sign-up", "/invalid-session").permitAll())
            .httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()))
            .exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
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
