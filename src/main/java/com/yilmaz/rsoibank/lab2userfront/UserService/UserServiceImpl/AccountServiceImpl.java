package com.yilmaz.rsoibank.lab2userfront.UserService.UserServiceImpl;

import com.yilmaz.rsoibank.lab2userfront.UserService.AccountService;
import com.yilmaz.rsoibank.lab2userfront.UserService.TransactionService;
import com.yilmaz.rsoibank.lab2userfront.UserService.UserService;
import com.yilmaz.rsoibank.lab2userfront.dao.PrimaryAccountDao;
import com.yilmaz.rsoibank.lab2userfront.dao.PrimaryTransactionDao;
import com.yilmaz.rsoibank.lab2userfront.dao.SavingsAccountDao;
import com.yilmaz.rsoibank.lab2userfront.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {

    private static int nextAccountNumber = 1212121;

    private PrimaryAccountDao primaryAccountDao;
    private SavingsAccountDao savingsAccountDao;
    private UserService userService;
    private TransactionService transactionService;

    @Autowired
    public void setPrimaryAccountDao(PrimaryAccountDao primaryAccountDao){
        this.primaryAccountDao = primaryAccountDao;
    }
    public PrimaryAccountDao getPrimaryAccountDao() {
        return primaryAccountDao;
    }

    @Autowired
    public void setSavingsAccountDao(SavingsAccountDao savingsAccountDao){
        this.savingsAccountDao = savingsAccountDao;
    }
    public SavingsAccountDao getSavingsAccountDao() {
        return savingsAccountDao;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    public UserService getuserService() {
        return userService;
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService){
        this.transactionService = transactionService;
    }
    public TransactionService getTransactionService() {
        return transactionService;
    }

    public PrimaryAccount createPrimaryAccount() {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.setAccountNumber(accountGen());

        primaryAccountDao.save(primaryAccount);

        return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());

    }

    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(new BigDecimal(0.0));
        savingsAccount.setAccountNumber(accountGen());

        savingsAccountDao.save(savingsAccount);

        return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
    }

    public void deposit(String accountType, double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("Primary")) {
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Deposit to Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
            transactionService.savePrimaryDepositTransaction(primaryTransaction);

        } else if (accountType.equalsIgnoreCase("Savings")) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Deposit to savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingDepositTranscation(savingsTransaction);
        }
    }

    public void withdraw(String accountType, double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("Primary")) {
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Withdraw from Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
            transactionService.savePrimaryWithdrawTransaction(primaryTransaction);

        } else if (accountType.equalsIgnoreCase("Savings")) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Withdraw from Savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingWithdrawTranscation(savingsTransaction);
        }
    }

    private int accountGen() {
        return ++nextAccountNumber;
    }
}
