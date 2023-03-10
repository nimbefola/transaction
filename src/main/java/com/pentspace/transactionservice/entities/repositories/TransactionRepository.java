package com.pentspace.transactionservice.entities.repositories;

import com.pentspace.transactionservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Optional<Transaction> findBySourceAccount(String sourceAccount);
}
