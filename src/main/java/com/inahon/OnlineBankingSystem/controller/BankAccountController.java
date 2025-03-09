package com.inahon.OnlineBankingSystem.controller;

import com.inahon.OnlineBankingSystem.service.BankAccountService;
import com.inahon.OnlineBankingSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{userId}/balance")
    public Double getBalance(@PathVariable Long userId) {
        return bankAccountService.checkBalance(userId);
    }

    @PostMapping("/{userId}/deposit")
    public String deposit(@PathVariable Long userId, @RequestParam Double amount) {
        bankAccountService.deposit(userId, amount);
        transactionService.recordTransaction(userId, amount, "DEPOSIT");
        return "Deposit of $" + amount + " successful.";
    }

    @PostMapping("/{userId}/withdraw")
    public String withdraw(@PathVariable Long userId, @RequestParam Double amount) {
        bankAccountService.withdraw(userId, amount);
        transactionService.recordTransaction(userId, amount, "WITHDRAWAL");
        return "Withdrawal of $" + amount + " successful.";
    }
}
