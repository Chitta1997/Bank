package com.example.bank.frontModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Customers {

   String customerName ;

   String customerPhone ;

    String customerEmail ;

}
