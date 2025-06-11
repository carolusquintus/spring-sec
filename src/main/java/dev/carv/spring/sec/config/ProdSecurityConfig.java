package dev.carv.spring.sec.config;

import dev.carv.spring.sec.exception.handler.CustomAccessDeniedHandler;
import dev.carv.spring.sec.exception.handler.CustomBasicAuthenticationEntryPoint;
import dev.carv.spring.sec.filter.CsrfCookieFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import static org.springframework.security.config.http.SessionCreationPolicy.ALWAYS;

@Configuration
@Profile("prod")
public class ProdSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var csrfTokenHandler = new CsrfTokenRequestAttributeHandler();
        return http
            .securityContext(secContext -> secContext.requireExplicitSave(false))
            .sessionManagement(session -> session.sessionCreationPolicy(ALWAYS))
            .requiresChannel(channel -> channel.anyRequest().requiresSecure())
            .cors(CorsConfig::getHttpSecurityCorsConfigurer)
            .csrf(csrfConfig -> csrfConfig
                .ignoringRequestMatchers("/contact", "customer/sign-up")
                .csrfTokenRequestHandler(csrfTokenHandler)
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/account").hasAuthority("VIEW_ACCOUNT")
                .requestMatchers("/balance").hasAnyAuthority("VIEW_BALANCE", "VIEW_ACCOUNT")
                .requestMatchers("/card").hasAuthority("VIEW_CARDS")
                .requestMatchers("/loan").hasAuthority("VIEW_LOANS")
                .requestMatchers("/customer/info").authenticated()
                .requestMatchers("/contact", "/notice", "/error", "/customer/sign-up", "/invalid-session").permitAll())
            .httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()))
            .exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()))
            .build();
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
