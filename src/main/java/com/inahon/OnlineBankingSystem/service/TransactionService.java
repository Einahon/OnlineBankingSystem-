package com.inahon.OnlineBankingSystem.service;

import com.inahon.OnlineBankingSystem.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactions(Long userId);
    void recordTransaction(Long userId, Double amount, String type);
}
