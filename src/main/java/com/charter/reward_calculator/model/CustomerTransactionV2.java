package com.charter.reward_calculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerTransactionV2 {

    private String customerName;
    private String customerAddress;
    private String customerPhoneNumber;
    private Integer transactionMonth;
    private Long transactionAmount;

}
