package com.charter.reward_calculator.service;

import com.charter.reward_calculator.model.CustomerTransaction;
import com.charter.reward_calculator.model.Reward;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {

    @Override
    public Reward getReward(CustomerTransaction customerTransaction) {
        Reward reward=new Reward();
        reward.setCustomerName(customerTransaction.getCustomerName());
        reward.setCustomerAddress(customerTransaction.getCustomerAddress());
        reward.setFirstMonthReward(getMonthlyReward(customerTransaction.getFirstMonthTransactions()));
        reward.setSecondMonthReward(getMonthlyReward(customerTransaction.getSecondMonthTransactions()));
        reward.setThirdMonthReward(getMonthlyReward(customerTransaction.getThirdMonthTransactions()));
        reward.setTotalReward(reward.getFirstMonthReward()+reward.getSecondMonthReward()+reward.getThirdMonthReward());
        return reward;
    }

    private Long getMonthlyReward(List<Long> monthlyTransactions) {
        Long reward=0L;
        for(Long aLong:monthlyTransactions){
            if(aLong>=100l){
                reward+=(aLong-100l)*2+50l;
            } else if(aLong>50){
                reward+=(aLong-50l);
            }
        }
        return reward;
    }
}
