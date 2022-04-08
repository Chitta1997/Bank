package com.example.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "TRANSACTIONS_TYPES")
public class TransactionsTypesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_type_id")
    private int transactionTypeID;

    @Column(name="transaction_type")
    private String transactionType;
}

