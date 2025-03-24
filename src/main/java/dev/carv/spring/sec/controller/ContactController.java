package dev.carv.spring.sec.controller;

import dev.carv.spring.sec.model.Contact;
import dev.carv.spring.sec.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.random.RandomGenerator;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactRepository repository;

    @PostMapping("/contact")
    public Contact saveContactInquiry(@RequestBody Contact contact) {
        var random = RandomGenerator.getDefault();
        contact.setId("SR-%s".formatted(random.nextInt(1000, 9999)));
        contact.setCreatedDateTime(LocalDateTime.now(ZoneId.of("UTC")));
        return repository.save(contact);
    }

}
