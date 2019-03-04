package com.orderMeal.domain;

public class Rules {
    private int ruleId;
    private int orderTime;
    private int orderTimes;
    private int changeTime;
    private int changeTimes;
    private int returnTime;
    private int returnTimes;

    public int getRuleId() {
        return ruleId;
    }

    public Rules(int ruleId, int orderTime, int orderTimes, int changeTime, int changeTimes, int returnTime, int returnTimes) {
        this.ruleId = ruleId;
        this.orderTime = orderTime;
        this.orderTimes = orderTimes;
        this.changeTime = changeTime;
        this.changeTimes = changeTimes;
        this.returnTime = returnTime;
        this.returnTimes = returnTimes;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public Rules() {
    }

    public int getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(int orderTime) {
        this.orderTime = orderTime;
    }

    public int getOrderTimes() {
        return orderTimes;
    }

    public void setOrderTimes(int orderTimes) {
        this.orderTimes = orderTimes;
    }

    public int getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(int changeTime) {
        this.changeTime = changeTime;
    }

    public int getChangeTimes() {
        return changeTimes;
    }

    public void setChangeTimes(int changeTimes) {
        this.changeTimes = changeTimes;
    }

    public int getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(int returnTime) {
        this.returnTime = returnTime;
    }

    public int getReturnTimes() {
        return returnTimes;
    }

    public void setReturnTimes(int returnTimes) {
        this.returnTimes = returnTimes;
    }

    @Override
    public String toString() {
        return "Rules{" +
                "ruleId=" + ruleId +
                ", orderTime=" + orderTime +
                ", orderTimes=" + orderTimes +
                ", changeTime=" + changeTime +
                ", changeTimes=" + changeTimes +
                ", returnTime=" + returnTime +
                ", returnTimes=" + returnTimes +
                '}';
    }
}
