package dev.carv.spring.sec.repository;

import dev.carv.spring.sec.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByCustomerId(Long customerId);

}
