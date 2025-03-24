package dev.carv.spring.sec.controller;

import dev.carv.spring.sec.model.Notice;
import dev.carv.spring.sec.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeRepository repository;

    @GetMapping("/notice")
    public ResponseEntity<List<Notice>> getNotice() {
        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
            .body(repository.findAllActiveNotices());
    }

}
