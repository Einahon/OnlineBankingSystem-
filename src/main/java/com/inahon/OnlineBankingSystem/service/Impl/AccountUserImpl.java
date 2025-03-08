package com.inahon.OnlineBankingSystem.service.Impl;

import com.inahon.OnlineBankingSystem.model.AccountUser;
import com.inahon.OnlineBankingSystem.repository.AccountUserRepository;
import com.inahon.OnlineBankingSystem.service.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountUserImpl implements AccountUserService {
    @Autowired
    private AccountUserRepository accountUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AccountUser registerUser(AccountUser accountUser) {
        accountUser.setPassword(passwordEncoder.encode(accountUser.getPassword()));
        return accountUserRepository.save(accountUser);
    }

    @Override
    public AccountUser loginUser(String username, String password) {
       AccountUser acctUser = accountUserRepository.findByUsername(username);
        if(acctUser != null && passwordEncoder.matches(password, acctUser.getPassword())) {
            return acctUser;
        }
        throw new RuntimeException("Invalid username or password");
    }
}
