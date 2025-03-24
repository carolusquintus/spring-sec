package dev.carv.spring.sec.controller;

import dev.carv.spring.sec.model.Loan;
import dev.carv.spring.sec.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanRepository repository;

    @GetMapping("/loan")
    public List<Loan> getLoan(@RequestParam Long id) {
        return repository.findByCustomerIdOrderByStartDateDesc(id);
    }

}

