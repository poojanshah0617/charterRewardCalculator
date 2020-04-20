package com.charter.reward_calculator.service;

import com.charter.reward_calculator.model.CustomerTransaction;
import com.charter.reward_calculator.model.Reward;

public interface RewardService {
    Reward getReward(CustomerTransaction customerTransaction);
}
