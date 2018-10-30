package com.yilmaz.rsoibank.lab2userfront.UserService.UserServiceImpl;


import com.yilmaz.rsoibank.lab2userfront.UserService.TransactionService;
import com.yilmaz.rsoibank.lab2userfront.UserService.UserService;
import com.yilmaz.rsoibank.lab2userfront.dao.PrimaryTransactionDao;
import com.yilmaz.rsoibank.lab2userfront.dao.SavingsTransactionDao;
import com.yilmaz.rsoibank.lab2userfront.domain.PrimaryTransaction;
import com.yilmaz.rsoibank.lab2userfront.domain.SavingsTransaction;
import com.yilmaz.rsoibank.lab2userfront.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private UserService userService;
    private PrimaryTransactionDao primaryTransactionDao;
    private SavingsTransactionDao savingsTransactionDao;

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
