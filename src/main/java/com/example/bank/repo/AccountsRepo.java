package com.example.bank.repo;

import com.example.bank.entity.AccountsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepo extends CrudRepository<AccountsEntity,Integer> {

    float getCurrentBalanceByAccountId(int accountId);
    AccountsEntity findByCustomerId(int customerId);
}
