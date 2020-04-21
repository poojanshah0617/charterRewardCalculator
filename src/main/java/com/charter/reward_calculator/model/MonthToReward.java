package com.charter.reward_calculator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MonthToReward {
    private String month;
    private Long reward;
    private List<Long>transactionsList;
}
