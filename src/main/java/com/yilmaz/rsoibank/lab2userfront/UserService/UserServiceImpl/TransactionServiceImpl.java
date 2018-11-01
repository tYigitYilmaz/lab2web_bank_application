package com.yilmaz.rsoibank.lab2userfront.UserService.UserServiceImpl;


import com.yilmaz.rsoibank.lab2userfront.UserService.TransactionService;
import com.yilmaz.rsoibank.lab2userfront.UserService.UserService;
import com.yilmaz.rsoibank.lab2userfront.dao.PrimaryAccountDao;
import com.yilmaz.rsoibank.lab2userfront.dao.PrimaryTransactionDao;
import com.yilmaz.rsoibank.lab2userfront.dao.SavingsAccountDao;
import com.yilmaz.rsoibank.lab2userfront.dao.SavingsTransactionDao;
import com.yilmaz.rsoibank.lab2userfront.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.DatabaseMetaData;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private UserService userService;
    private PrimaryTransactionDao primaryTransactionDao;
    private SavingsTransactionDao savingsTransactionDao;
    private PrimaryAccountDao primaryAccountDao;
    private SavingsAccountDao savingsAccountDao;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setPrimaryTransactionDao(PrimaryTransactionDao primaryTransactionDao){
        this.primaryTransactionDao = primaryTransactionDao;
    }
    public PrimaryTransactionDao getPrimaryTransactionDao() {
        return primaryTransactionDao;
    }

    @Autowired
    public void setSavingsTransactionDao(SavingsTransactionDao savingsTransactionDao){
        this.savingsTransactionDao = savingsTransactionDao;
    }
    public SavingsTransactionDao getSavingsTransactionDao() {
        return savingsTransactionDao;
    }

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


    public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<PrimaryTransaction> primaryTransactionList = user.getPrimaryAccount().getPrimaryTransactionList();

        return primaryTransactionList;
    }

    public List<SavingsTransaction> findSavingsTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionList();

        return savingsTransactionList;
    }

    public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception {
        if (transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Savings")) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Between account transfer from "+transferFrom+" to "+transferTo, "Account", "Finished", Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);
        } else if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Primary")) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Between account transfer from "+transferFrom+" to "+transferTo, "Transfer", "Finished", Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount);
            savingsTransactionDao.save(savingsTransaction);
        } else {
            throw new Exception("Invalid Transfer");
        }
    }


    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
        primaryTransactionDao.save(primaryTransaction);
    }

    public void saveSavingDepositTranscation(SavingsTransaction savingsTransaction) {
        savingsTransactionDao.save(savingsTransaction);
    }

    public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction) {
        primaryTransactionDao.save(primaryTransaction);
    }

    public void saveSavingWithdrawTranscation(SavingsTransaction savingsTransaction) {
        savingsTransactionDao.save(savingsTransaction);
    }
}
