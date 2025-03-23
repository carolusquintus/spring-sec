package dev.carv.spring.sec.repository;

import dev.carv.spring.sec.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findByCustomerIdOrderByTransactionDateTimeDesc(Long customerId);

}
