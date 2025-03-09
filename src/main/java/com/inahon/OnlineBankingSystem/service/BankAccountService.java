package com.inahon.OnlineBankingSystem.service;

import com.inahon.OnlineBankingSystem.model.AccountUser;
import com.inahon.OnlineBankingSystem.model.BankAccount;

public interface BankAccountService {
    BankAccount createAccount(AccountUser accountUser);
    BankAccount getAccountByUserId(Long userId);
    Double checkBalance(Long userId);
    void deposit(Long userId, Double amount);
    void withdraw(Long userId, Double amount);
}
