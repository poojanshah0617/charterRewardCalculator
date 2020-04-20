package com.charter.reward_calculator.controller;

import com.charter.reward_calculator.RewardCalculatorApplicationTests;
import com.charter.reward_calculator.model.CustomerTransaction;
import com.charter.reward_calculator.model.Reward;
import com.charter.reward_calculator.service.RewardService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;

public class RewardControllerTest extends RewardCalculatorApplicationTests {

   @Autowired
    RewardController rewardController;

    @Test
    public void getRewardTest(){
        CustomerTransaction customerTransaction=new CustomerTransaction("ABCD","123 XYZ STREET",new ArrayList(Arrays.asList(101L,49L)),new ArrayList(Arrays.asList(100L,120L,130L)),new ArrayList(Arrays.asList(50L,99L,49L)));
        Reward reward=(Reward)rewardController.getReward(customerTransaction).getBody();
        Assert.assertEquals("ABCD",reward.getCustomerName());
        Assert.assertEquals("First month reward should be 52",new Long(52),reward.getFirstMonthReward());
        Assert.assertEquals("Second month reward should be 250",new Long(250),reward.getSecondMonthReward());
        Assert.assertEquals("Third month reward should be 49",new Long(49),reward.getThirdMonthReward());
        Assert.assertEquals("Total reward should be 349",new Long(351),reward.getTotalReward());
    }
}
