package com.example.bank.repo;

import com.example.bank.entity.TransactionsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepo extends CrudRepository<TransactionsEntity,Integer> {

}
