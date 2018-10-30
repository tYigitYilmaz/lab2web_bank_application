package com.yilmaz.rsoibank.lab2userfront.dao;

import com.yilmaz.rsoibank.lab2userfront.domain.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction, Long> {

    List<SavingsTransaction> findAll();
}
