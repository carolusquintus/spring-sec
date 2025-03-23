package dev.carv.spring.sec.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    private Long accountNumber;

    private Long customerId;

    @Column(name = "transaction_at")
    private LocalDateTime transactionDateTime;

    private String transactionSummary;

    private String transactionType;

    private Integer transactionAmount;

    private Integer closingBalance;

    @Column(name = "created_at")
    private LocalDateTime createdDateTime;

}
