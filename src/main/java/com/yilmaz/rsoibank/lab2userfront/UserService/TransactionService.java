package com.yilmaz.rsoibank.lab2userfront.UserService;

import com.yilmaz.rsoibank.lab2userfront.domain.PrimaryAccount;
import com.yilmaz.rsoibank.lab2userfront.domain.PrimaryTransaction;
import com.yilmaz.rsoibank.lab2userfront.domain.SavingsAccount;
import com.yilmaz.rsoibank.lab2userfront.domain.SavingsTransaction;

import java.util.List;

public interface TransactionService {

    List<PrimaryTransaction> findPrimaryTransactionList(String username);
    List<SavingsTransaction> findSavingsTransactionList(String username);
    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);
    void saveSavingDepositTranscation(SavingsTransaction savingsTransaction);
    void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);
    void saveSavingWithdrawTranscation(SavingsTransaction savingsTransaction);

    void betweenAccountsTransfer(String tranferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception;

}