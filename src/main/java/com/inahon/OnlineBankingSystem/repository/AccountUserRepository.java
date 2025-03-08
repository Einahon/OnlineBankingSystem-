package com.inahon.OnlineBankingSystem.repository;

import com.inahon.OnlineBankingSystem.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountUserRepository extends JpaRepository<AccountUser, Long> {
    AccountUser findByEmail(String email);
}
