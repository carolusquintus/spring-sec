package dev.carv.spring.sec.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String mobileNumber;
    private String password;
    private String role;
    private boolean enabled;
    @Column(name = "created_at")
    private LocalDateTime createdDateTime;
    @Column(name = "updated_at")
    private LocalDateTime updatedDateTime;

}
