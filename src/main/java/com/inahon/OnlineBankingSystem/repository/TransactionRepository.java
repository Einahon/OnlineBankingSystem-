package com.inahon.OnlineBankingSystem.repository;

import com.inahon.OnlineBankingSystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByAccountId(Long accountId);
}
