package com.example.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "ACCOUNTS")
public class AccountsEntity {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int accountId;

    @Column(name="customer_id")
    private int customerId;

    @Column(name="date_opened")
    private Date dateOpened;


    @Column(name="date_closed")
    private Date dateClosed;

    @Column(name="minimum_balance")
    float minimumBalance;

    @Column(name="current_balance")
    float currentBalance;

}
