package dev.carv.spring.sec.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    private String noticeSummary;

    private String noticeDetail;

    private LocalDate noticeBeginDate;

    private LocalDate noticeEndDate;

    @Column(name = "created_at")
    private LocalDateTime createdDateTime;

    @Column(name = "updated_at")
    private LocalDateTime updatedDateTime;

}
