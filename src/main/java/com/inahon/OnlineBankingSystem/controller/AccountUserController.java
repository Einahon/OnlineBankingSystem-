package com.inahon.OnlineBankingSystem.controller;

import com.inahon.OnlineBankingSystem.model.AccountUser;
import com.inahon.OnlineBankingSystem.service.AccountUserService;
import com.inahon.OnlineBankingSystem.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AccountUserController {
    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/register")
    public AccountUser registerUser(@RequestBody AccountUser accountUser) {
        AccountUser savedUser = accountUserService.registerUser(accountUser);
        bankAccountService.createAccount(savedUser); // Create a bank account upon registration
        return savedUser;
    }

    @PostMapping("/login")
    public AccountUser loginUser(@RequestParam String email, @RequestParam String password) {
        return accountUserService.loginUser(email, password);
    }
}
