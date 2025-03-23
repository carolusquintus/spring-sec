package dev.carv.spring.sec.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "contact_message")
public class Contact {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "contact_id")
    private Long id;

    private String contactName;

    private String contactEmail;

    private String subject;

    private String message;

    @Column(name = "created_at")
    private LocalDateTime createdDateTime;

}
