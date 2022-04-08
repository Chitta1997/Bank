package com.example.bank.repo;

import com.example.bank.entity.TransactionsTypesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsTypesRepo extends CrudRepository<TransactionsTypesEntity,Integer> {
}
