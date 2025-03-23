package dev.carv.spring.sec.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private Long id;

    private String cardNumber;

    private Long customerId;

    private String cardType;

    private Integer totalLimit;

    private Integer amountUsed;

    private Integer availableAmount;

    @Column(name = "created_at")
    private LocalDateTime createdDateTime;

}
