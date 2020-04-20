package com.charter.reward_calculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerTransaction {
    private String customerName;
    private String customerAddress;
    private List<Long>firstMonthTransactions;
    private List<Long>secondMonthTransactions;
    private List<Long>thirdMonthTransactions;
}
