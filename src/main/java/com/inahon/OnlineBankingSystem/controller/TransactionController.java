package com.inahon.OnlineBankingSystem.controller;

import com.inahon.OnlineBankingSystem.model.Transaction;
import com.inahon.OnlineBankingSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable Long userId) {
        return transactionService.getTransactions(userId);
    }
}
