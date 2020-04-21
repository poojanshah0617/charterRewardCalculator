package com.charter.reward_calculator.service;

import com.charter.reward_calculator.model.CustomerTransaction;
import com.charter.reward_calculator.model.Reward;
import com.charter.reward_calculator.model.RewardV2;

import java.util.List;

public interface RewardService {
    Reward getReward(CustomerTransaction customerTransaction);

    List<RewardV2> getRewardV2();
}
