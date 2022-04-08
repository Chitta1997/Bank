package com.example.bank.controller;

import com.example.bank.frontModel.Customers;
import com.example.bank.service.BankServiceImpl;
import com.example.bank.service.BankServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BankController {

@Autowired
BankServiceInterface bankService;


    @PostMapping("/createAccount")
    public ResponseEntity<String> createAccount(@RequestBody Customers customers){
        try {
            return new ResponseEntity(bankService.openAccount(customers), HttpStatus.ACCEPTED);
        }catch (Exception e){
            throw e;
        }
    }

    @PostMapping("/{accountId}/deposit/{depo}")
    public ResponseEntity deposit(@PathVariable int accountId,@PathVariable float depo){
    try {
        return new ResponseEntity<>(bankService.depositAmount(accountId, depo), HttpStatus.OK);
    }
    catch(Exception e){
        throw e;
    }
    }

    @PostMapping("/{accountId}/withdraw/{withdraw}")
    public ResponseEntity withdrawl(@PathVariable int accountId,@PathVariable float withdraw) {
    try{
        return new ResponseEntity<>(bankService.withdrawMoney(accountId, withdraw), HttpStatus.OK);
    }  catch(Exception e){
            throw e;
        }
    }

    @PutMapping("/{customerId}/close")
    public ResponseEntity closeAccount(@PathVariable int customerId ){
        try{
        return new ResponseEntity(bankService.close(customerId),HttpStatus.ACCEPTED);
    }  catch(Exception e){
        throw e;
    }
    }

}
