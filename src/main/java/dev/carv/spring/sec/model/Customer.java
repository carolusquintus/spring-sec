package dev.carv.spring.sec.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
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

    @JsonProperty(access = WRITE_ONLY)
    private String password;

    private String role;

    private boolean enabled;

    @Column(name = "created_at")
    private LocalDateTime createdDateTime;

    @Column(name = "updated_at")
    private LocalDateTime updatedDateTime;

    @OneToMany(mappedBy = "customer", cascade = ALL, fetch = EAGER)
    @JsonIgnore
    private Set<Authority> authorities;

}
