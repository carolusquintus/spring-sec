package dev.carv.spring.sec.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @Column(name = "contact_id")
    private String id;

    private String contactName;

    private String contactEmail;

    private String subject;

    private String message;

    @Column(name = "created_at")
    private LocalDateTime createdDateTime;

}
