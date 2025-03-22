package dev.carv.spring.sec.controller;

import dev.carv.spring.sec.model.Customer;
import dev.carv.spring.sec.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository repository;
    private final PasswordEncoder encoder;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody Customer customer) {
        try{
            var hashedPassword = encoder.encode(customer.getPassword());
            customer.setPassword(hashedPassword);
            customer.setEnabled(true);
            customer.setCreatedDateTime(LocalDateTime.now(ZoneId.of("UTC")));
            var savedCustomer = repository.save(customer);

            if (nonNull(savedCustomer.getId())) {
                return ResponseEntity
                    .status(CREATED)
                    .body("User registered successfully");
            }
            return ResponseEntity
                .badRequest()
                .body("User registration failed");
        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body("User registration failed: %s".formatted(e.getMessage()));
        }
    }

}
