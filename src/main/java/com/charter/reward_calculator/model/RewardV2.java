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
public class RewardV2 {
       private String customerName;
       private List<MonthToReward> reward;
       private  Long totalReward;
}
