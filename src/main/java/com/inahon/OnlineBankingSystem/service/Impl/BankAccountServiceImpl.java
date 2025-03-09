package com.inahon.OnlineBankingSystem.service.Impl;

import com.inahon.OnlineBankingSystem.model.AccountUser;
import com.inahon.OnlineBankingSystem.model.BankAccount;
import com.inahon.OnlineBankingSystem.repository.BankAccountRepository;
import com.inahon.OnlineBankingSystem.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public BankAccount createAccount(AccountUser accountUser) {
        BankAccount account = new BankAccount();
        account.setAccountUser(accountUser);
        account.setAccountNumber(UUID.randomUUID().toString()); // Generate unique account number
        account.setBalance(0.0);
        return bankAccountRepository.save(account);
    }

    @Override
    public BankAccount getAccountByUserId(Long userId) {
        if(bankAccountRepository.existsById(userId)) {
            return bankAccountRepository.findById(userId).get();
        }
        return null;
    }

    @Override
    public Double checkBalance(Long userId) {
        return getAccountByUserId(userId).getBalance();
    }

    @Override
    public void deposit(Long userId, Double amount) {
        BankAccount account = getAccountByUserId(userId);
        account.setBalance(account.getBalance() + amount);
        bankAccountRepository.save(account);

    }

    @Override
    public void withdraw(Long userId, Double amount) {
        BankAccount account = getAccountByUserId(userId);
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        bankAccountRepository.save(account);
    }


}
