package dev.carv.spring.sec.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @Column(name = "created_at")
    private LocalDateTime createdDateTime;

    @JsonIgnore
    @Column(name = "updated_at")
    private LocalDateTime updatedDateTime;

}
