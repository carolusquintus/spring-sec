package dev.carv.spring.sec.controller;

import dev.carv.spring.sec.model.Transaction;
import dev.carv.spring.sec.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final TransactionRepository repository;

    @GetMapping("/balance")
    public List<Transaction> getBalance(@RequestParam("customer_id") Long customer_id) {
        return repository.findByCustomerIdOrderByTransactionDateTimeDesc(customer_id);
    }

}
