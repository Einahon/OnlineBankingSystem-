package com.inahon.OnlineBankingSystem.service.Impl;

import com.inahon.OnlineBankingSystem.model.BankAccount;
import com.inahon.OnlineBankingSystem.model.Transaction;
import com.inahon.OnlineBankingSystem.model.TransactionType;
import com.inahon.OnlineBankingSystem.repository.TransactionRepository;
import com.inahon.OnlineBankingSystem.service.BankAccountService;
import com.inahon.OnlineBankingSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @Override
    public List<Transaction> getTransactions(Long userId) {
        BankAccount account = bankAccountService.getAccountByUserId(userId);
        return transactionRepository.findAll();

    }

    @Override
    public void recordTransaction(Long userId, Double amount, String type) {
        BankAccount account = bankAccountService.getAccountByUserId(userId);

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .type(TransactionType.valueOf(type.toUpperCase()))
                .timestamp(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);
    }
}
