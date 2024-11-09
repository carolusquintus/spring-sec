package dev.carv.spring.sec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @GetMapping("/card")
    public String getCard() {
        return "get card";
    }

}
