package com.orderMeal.domain;


import com.orderMeal.dao.GoodsDao;

import java.util.Date;

public class Orders {
    private int orderId; //订单id
    private int goodsId;  //套餐id
    private int schoolId;  //学校id
    private int customerGrade;  //客户年级
    private int customerClass;  //客户班级
    private int customerId;  //客户id
    private String customerNum;  //客户编号（学号
    private double goodsPrice;  //套餐价格
    private String productName;  //菜品拼接
    private String orderDelivery;  //配送日期
    private int deliveryDate;  //配送时段 （午餐或晚餐 1，2
    private String orderDate;  //下单时间
    private int orderStatus;  //订单状态  1.下单  2.已支付  3.订单完成  0.订单取消
    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(int goodsId) {
        this.goods = GoodsDao.findGoodsById(String.valueOf(goodsId));
    }

    public String getOrderDelivery() {
        return orderDelivery;
    }

    public void setOrderDelivery(String orderDelivery) {
        this.orderDelivery = orderDelivery;
    }

    public int getCustomerGrade() {
        return customerGrade;
    }

    public void setCustomerGrade(int customerGrade) {
        this.customerGrade = customerGrade;
    }

    public int getCustomerClass() {
        return customerClass;
    }

    public void setCustomerClass(int customerClass) {
        this.customerClass = customerClass;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(int deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
}
