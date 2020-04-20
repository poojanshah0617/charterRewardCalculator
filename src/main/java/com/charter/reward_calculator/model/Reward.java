package com.charter.reward_calculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Reward {
   private String customerName;
    private String customerAddress;
    private Long firstMonthReward;
    private Long secondMonthReward;
    private Long thirdMonthReward;
    private Long totalReward;
}
