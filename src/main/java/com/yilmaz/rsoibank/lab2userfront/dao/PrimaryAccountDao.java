package com.yilmaz.rsoibank.lab2userfront.dao;

import com.yilmaz.rsoibank.lab2userfront.domain.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long> {

    PrimaryAccount findByAccountNumber (int accountNumber);
}
