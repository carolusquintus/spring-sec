package dev.carv.spring.sec.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "account_number")
    private Long id;

    private Long customerId;

    private String accountType;

    private String branchAddress;

    @Column(name = "created_at")
    private LocalDateTime createdDateTime;

}
