package dev.carv.spring.sec.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("eazybank-error-reason", "Authentication failed");
        response.setStatus(UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");

        var mapResponse = Map.of(
            "timestamp", LocalDateTime.now(),
            "status",    UNAUTHORIZED.value(),
            "error",     UNAUTHORIZED.getReasonPhrase(),
            "message",   Optional.ofNullable(authException).map(AuthenticationException::getMessage).orElse("Unauthorized"),
            "path",      request.getRequestURI()
        );

        var jsonResponse = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(WRITE_DATES_AS_TIMESTAMPS)
            .writeValueAsString(mapResponse);

        response.getWriter().write(jsonResponse);
    }

}
