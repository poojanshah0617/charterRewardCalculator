package com.charter.reward_calculator.controller;

import com.charter.reward_calculator.model.CustomerTransaction;
import com.charter.reward_calculator.service.RewardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardController {
    private RewardService rewardService;
    public RewardController(RewardService rewardService){
        this.rewardService=rewardService;
    }

    @RequestMapping(path="/api/getReward",method= RequestMethod.POST)
    public ResponseEntity getReward(@RequestBody CustomerTransaction customerTransaction){
       return new ResponseEntity(rewardService.getReward(customerTransaction), HttpStatus.OK);
    }

    @RequestMapping(path="/api/getReward",method= RequestMethod.GET)
    public ResponseEntity getRewardV2(){
       return new ResponseEntity(rewardService.getRewardV2(), HttpStatus.OK);
    }
}
