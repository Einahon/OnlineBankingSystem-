package com.inahon.OnlineBankingSystem.repository;

import com.inahon.OnlineBankingSystem.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findByAccountNumber(String accountNumber);

}
