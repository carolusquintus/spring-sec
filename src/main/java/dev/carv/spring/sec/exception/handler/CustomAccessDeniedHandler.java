package dev.carv.spring.sec.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static org.springframework.http.HttpStatus.FORBIDDEN;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setHeader("eazybank-denied-reason", "Authentication failed");
        response.setStatus(FORBIDDEN.value());
        response.setContentType("application/json;charset=UTF-8");

        var mapResponse = Map.of(
            "timestamp", LocalDateTime.now(),
            "status",    FORBIDDEN.value(),
            "error",     FORBIDDEN.getReasonPhrase(),
            "message",   Optional.ofNullable(accessDeniedException).map(AccessDeniedException::getMessage).orElse("Unauthorized"),
            "path",      request.getRequestURI()
        );

        var jsonResponse = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(WRITE_DATES_AS_TIMESTAMPS)
            .writeValueAsString(mapResponse);

        response.getWriter().write(jsonResponse);

    }

}
