package dev.carv.spring.sec.controller;

import dev.carv.spring.sec.model.Account;
import dev.carv.spring.sec.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository repository;

    @GetMapping("/account")
    public Account getAccount(@RequestParam Long id) {
        return repository.findByCustomerId(id).orElse(null);
    }

}
