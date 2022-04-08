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
@Table(name = "TRANSACTIONS")
public class TransactionsEntity {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;

    @Column(name = "account_id")
    private int accountId;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "transaction_type_id")
    private int transactionTypeId;

    @Column(name = "transaction_amount")
    private float transactionAmount;

    @Column(name = "transaction_note")
    private String transactionNote;

    @Column(name = "balance")
    private float balance;

}
