package dev.carv.spring.sec.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationEvents {

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent  success) {
        log.info("Authentication success for user {}", success.getAuthentication().getName());
    }

    @EventListener
    public void onFail(AbstractAuthenticationFailureEvent fail) {
        log.error("Authentication failed for user {} due to {}",
            fail.getAuthentication().getName(), fail.getException().getMessage());
    }

}
