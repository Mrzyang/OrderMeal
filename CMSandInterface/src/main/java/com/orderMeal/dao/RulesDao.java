package com.orderMeal.dao;

import com.orderMeal.domain.Rules;

import java.util.List;
import java.util.Map;

public class RulesDao extends BaseDao {

    /**
     * 根据id查找规则
     * @param id 规则ID
     * @return 查到的规则
     */
    public Rules getRules(int id){
        String sql = "select * from rules where rules_id = ?";
        List<Map<String,Object>> rulesList = execteQuery(sql,new Object[]{id});
        if(rulesList.size()>0){
            Map<String,Object> map = rulesList.get(0);
            int ruleId = Integer.parseInt(map.get("rules_id").toString());
            int orderTime = Integer.parseInt(map.get("order_time").toString());
            int orderTimes = Integer.parseInt(map.get("order_times").toString());
            int changeTime = Integer.parseInt(map.get("change_time").toString());
            int changeTimes = Integer.parseInt(map.get("change_times").toString());
            int returnTime = Integer.parseInt(map.get("return_time").toString());
            int returnTimes = Integer.parseInt(map.get("return_times").toString());
            Rules rules = new Rules(ruleId,orderTime,orderTimes,changeTime,changeTimes,returnTime,returnTimes);
            return rules;
        }
        return null;
    }

    /**
     * 更新规则
     * @param rules 修改的规则
     * @return 是否修改成功
     */
    public boolean updateRules(Rules rules){
        String sql = "update rules set order_time=?,order_times=?,change_time=?,change_times=?,return_time=?,return_times=? where rules_id=?";
        int count = executeUpdate(sql,new Object[]{rules.getOrderTime(),rules.getOrderTimes(),rules.getChangeTime(),rules.getChangeTimes(),rules.getReturnTime(),rules.getReturnTimes(),rules.getRuleId()});
        return count>0;
    }
}
