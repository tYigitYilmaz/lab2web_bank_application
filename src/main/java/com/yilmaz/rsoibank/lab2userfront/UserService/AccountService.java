package com.yilmaz.rsoibank.lab2userfront.UserService;

import com.yilmaz.rsoibank.lab2userfront.domain.PrimaryAccount;
import com.yilmaz.rsoibank.lab2userfront.domain.SavingsAccount;

import java.security.Principal;

public interface AccountService {

    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
    public void deposit(String accountType, double amount, Principal principal);
}
