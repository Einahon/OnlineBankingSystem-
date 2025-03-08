package com.inahon.OnlineBankingSystem.service;

import com.inahon.OnlineBankingSystem.model.AccountUser;

public interface AccountUserService {
    AccountUser registerUser(AccountUser accountUser);          // For registration using email
    AccountUser loginUser(String username, String password);
}
