package com.example.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "CUSTOMERS")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomersEntity {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "date_opened")
    private Date dateOpened;

    @Column(name = "date_closed")
    private Date dateClosed;

}
