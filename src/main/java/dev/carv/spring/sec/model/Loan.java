package dev.carv.spring.sec.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "loan_number")
    private Long id;

    private Long customerId;

    private LocalDate startDate;

    private String loanType;

    private Integer totalLoan;

    private Integer amountPaid;

    private Integer outstandingAmount;

    @Column(name = "created_at")
    private LocalDateTime createdDateTime;

}
