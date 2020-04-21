package com.charter.reward_calculator.dao;

import com.charter.reward_calculator.model.CustomerTransactionV2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RewardDao {

    JdbcTemplate jdbcTemplate;

    public RewardDao(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }
    public List<CustomerTransactionV2> getRewardV2() {
        String sql="Select * from cust_details cust join trans_details trans on cust.cust_id=trans.cust_id";
        List<CustomerTransactionV2> custTransDetailList=new ArrayList<>();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                CustomerTransactionV2 customerTransactionV2 =new CustomerTransactionV2();
                customerTransactionV2.setCustomerName(resultSet.getString("cust_name"));
                 customerTransactionV2.setCustomerAddress(resultSet.getString("cust_add"));
                 customerTransactionV2.setCustomerPhoneNumber(resultSet.getString("cust_ph"));
                customerTransactionV2.setTransactionAmount(resultSet.getLong("trans_amt"));
                customerTransactionV2.setTransactionMonth(resultSet.getInt("trans_month"));
            custTransDetailList.add(customerTransactionV2);
            }
        });
        return custTransDetailList;
    }
}
