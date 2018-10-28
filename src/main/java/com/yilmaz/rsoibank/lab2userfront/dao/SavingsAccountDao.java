package com.yilmaz.rsoibank.lab2userfront.dao;

import com.yilmaz.rsoibank.lab2userfront.domain.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {

    SavingsAccount findByAccountNumber(int accountNumber);
}
