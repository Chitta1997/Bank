package com.example.bank.service;

import com.example.bank.entity.*;
import com.example.bank.exception.BankException;
import com.example.bank.frontModel.Customers;
import com.example.bank.repo.AccountsRepo;
import com.example.bank.repo.CustomersRepo;
import com.example.bank.repo.TransactionsRepo;
import com.example.bank.repo.TransactionsTypesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class BankServiceImpl implements BankServiceInterface{

    @Autowired
    AccountsRepo accountsRepo;

    @Autowired
    CustomersRepo customersRepo;

    @Autowired
    TransactionsRepo transactionsRepo;

    @Autowired
    TransactionsTypesRepo transactionsTypesRepo;

    @Transactional(rollbackOn = BankException.class)
    @Override
    public String openAccount(Customers customers) {
        try {
            CustomersEntity customersEntity = new CustomersEntity();

            customersEntity.setCustomerName(customers.getCustomerName());
            customersEntity.setCustomerEmail(customers.getCustomerEmail());
            customersEntity.setCustomerPhone(customers.getCustomerPhone());
            customersEntity.setDateOpened(new Date());

            CustomersEntity customersEntity1 = customersRepo.save(customersEntity);

            AccountsEntity accountsEntity = createAccount(customersEntity1.getCustomerId());
            AccountsEntity accountsEntity1 = accountsRepo.save(accountsEntity);
           // int q=5/0;
            return "your account has been successfully created, your account id is : " +
                    accountsEntity1.getAccountId();
        }catch (Exception e){
            throw new BankException("ROLL BACK ");
        }
    }

    private AccountsEntity createAccount(int customerId) {
        AccountsEntity accountsEntity = new AccountsEntity();
        accountsEntity.setCurrentBalance(100);
        accountsEntity.setMinimumBalance(10);
        accountsEntity.setDateOpened(new Date());
        accountsEntity.setCustomerId(customerId);
        return accountsEntity;
    }
    @Override
    public String depositAmount(int accountId, float depo) {
        Optional<AccountsEntity> accounts = accountsRepo.findById(accountId);
        if (accounts.isPresent()) {
            AccountsEntity accountsEntity=accounts.get();
            if(accountsEntity.getDateClosed()!= null){
                throw new BankException("account is already closed");
            }
            TransactionsEntity transactionsEntity = new TransactionsEntity();
            transactionsEntity.setAccountId(accountId);
            transactionsEntity.setTransactionAmount(depo);
            transactionsEntity.setTransactionDate(new Date());
            float bal =accountsEntity.getCurrentBalance() + depo;
            transactionsEntity.setBalance(bal);
            transactionsEntity.setTransactionNote(" Transaction Successful");

            TransactionsTypesEntity transactionsTypesEntity = transactionsTyp(TransactionType.DEPOSIT.name());
            TransactionsTypesEntity transactionsTypesEntity1 =
                    transactionsTypesRepo.save(transactionsTypesEntity);

            transactionsEntity.setTransactionTypeId(transactionsTypesEntity1.getTransactionTypeID());
            TransactionsEntity transactionsEntity1 = transactionsRepo.save(transactionsEntity);
            accountsEntity.setCurrentBalance(bal);
            accountsRepo.save(accountsEntity);

            return "Your transaction id is : " + transactionsEntity1.getTransactionId() + "updated balance : "
                    + transactionsEntity1.getBalance();
        } else
            throw new BankException("account doesnot exist");

    }

    private TransactionsTypesEntity transactionsTyp(String type) {
        TransactionsTypesEntity transactionsTypesEntity = new TransactionsTypesEntity();
        transactionsTypesEntity.setTransactionType(type);
        return transactionsTypesEntity;
    }

    @Override
    public String withdrawMoney(int accountId, float withdraw) {
        Optional<AccountsEntity> accounts = accountsRepo.findById(accountId);
        if (accounts.isPresent()) {
            AccountsEntity accountsEntity = accounts.get();
            if(accountsEntity.getDateClosed()!= null){
                throw new BankException("account is already closed");
            }
            float currentBalance = accountsEntity.getCurrentBalance();
            if (currentBalance < withdraw) {
                throw new BankException("insufficient balance");
            }
            TransactionsEntity transactionsEntity = new TransactionsEntity();
            transactionsEntity.setAccountId(accountId);
            transactionsEntity.setTransactionAmount(withdraw);
            transactionsEntity.setTransactionDate(new Date());


            float updatedBalance = currentBalance - withdraw;
            transactionsEntity.setBalance(updatedBalance);
            transactionsEntity.setTransactionNote(" Transaction Successful");


            TransactionsTypesEntity transactionsTypesEntity = transactionsTyp(TransactionType.WITHDRAW.name());
            TransactionsTypesEntity transactionsTypesEntity1 = transactionsTypesRepo.save(transactionsTypesEntity);

            transactionsEntity.setTransactionTypeId(transactionsTypesEntity1.getTransactionTypeID());

            TransactionsEntity transactionsEntity1 = transactionsRepo.save(transactionsEntity);
            accountsEntity.setCurrentBalance(updatedBalance);
            accountsRepo.save(accountsEntity);


            return "Your transaction id is : " + transactionsEntity1.getTransactionId() + "current balance : " + transactionsEntity1.getBalance();
        } else throw new BankException("account doesnot exist !!");

    }

    @Override
    public String close(int customerId){

       Optional<CustomersEntity> customersEntity=customersRepo.findById(customerId);
       if(customersEntity.isPresent()){
         CustomersEntity customersEntity1=  customersEntity.get();
         if(customersEntity1.getDateClosed()!= null){
             throw new BankException(" account is already closed ");
         }
         customersEntity1.setDateClosed(new Date());

         AccountsEntity accountsEntity=accountsRepo.findByCustomerId(customerId);
         if(accountsEntity==null) {
    throw  new BankException((" Account not found"));
         }
         accountsEntity.setDateClosed(new Date());
           customersRepo.save(customersEntity1);
         accountsRepo.save(accountsEntity);
       }
       return "account is closed on "+new Date();
    }




}
