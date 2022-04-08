package com.example.bank.service;

import com.example.bank.frontModel.Customers;

import javax.transaction.Transactional;

public interface BankServiceInterface {
    @Transactional(rollbackOn = Exception.class)
    String openAccount(Customers customers);

    String depositAmount(int accountId, float depo);

    String withdrawMoney(int accountId, float withdraw);

    String close(int customerId);
}
