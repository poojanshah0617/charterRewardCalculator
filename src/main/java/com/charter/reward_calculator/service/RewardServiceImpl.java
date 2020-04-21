package com.charter.reward_calculator.service;

import com.charter.reward_calculator.dao.RewardDao;
import com.charter.reward_calculator.model.CustomerTransactionV2;
import com.charter.reward_calculator.model.CustomerTransaction;
import com.charter.reward_calculator.model.MonthToReward;
import com.charter.reward_calculator.model.Reward;
import com.charter.reward_calculator.model.RewardV2;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService {
    private RewardDao rewardDao;
    public RewardServiceImpl(RewardDao rewardDao){
        this.rewardDao=rewardDao;
    }

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

    @Override
    public List<RewardV2> getRewardV2(){
        return  generateReward(rewardDao.getRewardV2());
    }

    private List<RewardV2> generateReward(List<CustomerTransactionV2> rewardV2) {
        Map<String,List<CustomerTransactionV2>> customerToTransaction=rewardV2.stream()
                                                                    .collect(Collectors.groupingBy(CustomerTransactionV2::getCustomerName));
        Map<String,Map<Integer,List<CustomerTransactionV2>>>custToMonthToTrans=new HashMap<>();
        for(Map.Entry<String, List<CustomerTransactionV2>> entry:customerToTransaction.entrySet()){
            custToMonthToTrans.put(entry.getKey()
                    ,entry.getValue().stream()
                            .filter(customerTransactionV2 -> (Calendar.getInstance().get(Calendar.MONTH)+1)
                                    - customerTransactionV2.getTransactionMonth()<=3&&(Calendar.getInstance().get(Calendar.MONTH)+1)
                                    - customerTransactionV2.getTransactionMonth()>0)
                            .collect(Collectors.groupingBy(CustomerTransactionV2::getTransactionMonth)));
        }
        List<RewardV2> rewardV2s=new ArrayList<>();
        for(Map.Entry<String,Map<Integer,List<CustomerTransactionV2>>> entry:custToMonthToTrans.entrySet()){
                RewardV2 reward=new RewardV2();
                List<MonthToReward> monthToRewards=new ArrayList<>();
                for(Map.Entry<Integer,List<CustomerTransactionV2>> entry1:entry.getValue().entrySet()) {
                    MonthToReward monthToReward = new MonthToReward();
                    monthToReward.setMonth(new DateFormatSymbols().getMonths()[Integer.valueOf(entry1.getKey())-1]);
                    AtomicReference<Long> points= new AtomicReference<>(new Long(0l));
                    entry1.getValue().forEach(customerTransactionV2 -> {
                        if(customerTransactionV2.getTransactionAmount()>=100){
                            points.updateAndGet(v -> v + (customerTransactionV2.getTransactionAmount() - 100) * 2 + 50);
                        }
                        else if(customerTransactionV2.getTransactionAmount()>50){
                            points.updateAndGet(v -> v + customerTransactionV2.getTransactionAmount() - 50);
                        }
                    });
                    monthToReward.setReward(points.get());
                    monthToReward.setTransactionsList(entry1.getValue().stream().map(CustomerTransactionV2::getTransactionAmount).collect(Collectors.toList()));
                    monthToRewards.add(monthToReward);
                }
                reward.setCustomerName(entry.getKey());
                reward.setReward(monthToRewards);
                reward.setTotalReward(monthToRewards.stream().map(monthToReward -> monthToReward.getReward()).reduce((s1,s2)->s1+s2).get());
                rewardV2s.add(reward);
        }
        return rewardV2s;
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
