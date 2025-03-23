package dev.carv.spring.sec.repository;

import dev.carv.spring.sec.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByCustomerId(Long customerId);

}
