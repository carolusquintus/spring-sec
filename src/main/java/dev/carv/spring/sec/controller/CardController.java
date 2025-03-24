package dev.carv.spring.sec.controller;

import dev.carv.spring.sec.model.Card;
import dev.carv.spring.sec.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardRepository repository;

    @GetMapping("/card")
    public List<Card> getCard(@RequestParam Long id) {
        return repository.findByCustomerId(id);
    }

}
