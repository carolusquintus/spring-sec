package dev.carv.spring.sec.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

public class CorsConfig {

    public static void getHttpSecurityCorsConfigurer(CorsConfigurer<HttpSecurity> cors) {
        cors.configurationSource(request -> {
            var config = new CorsConfiguration();
            config.setMaxAge(3_600L);
            config.setAllowCredentials(true);
            config.setAllowedMethods(List.of("*"));
            config.setAllowedHeaders(List.of("*"));
            config.setAllowedOrigins(List.of("http://localhost:4200"));
            return config;
        });
    }

}