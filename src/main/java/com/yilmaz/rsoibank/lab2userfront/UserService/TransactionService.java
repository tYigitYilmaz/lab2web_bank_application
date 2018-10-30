package com.yilmaz.rsoibank.lab2userfront.UserService;

import com.yilmaz.rsoibank.lab2userfront.domain.PrimaryTransaction;
import com.yilmaz.rsoibank.lab2userfront.domain.SavingsTransaction;

import java.security.Principal;
import java.util.List;

public interface TransactionService {

    List<PrimaryTransaction> findPrimaryTransactionList(String username);
    List<SavingsTransaction> findSavingsTransactionList(String username);
    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);
    void saveSavingDepositTranscation(SavingsTransaction savingsTransaction);
    void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);
    void saveSavingWithdrawTranscation(SavingsTransaction savingsTransaction);

}