package com.example.bank.repo;

import com.example.bank.entity.CustomersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepo extends CrudRepository<CustomersEntity,Integer> {

}
