package com.wap.service;


import com.google.gson.Gson;
import com.orderMeal.domain.Orders;
import com.wap.dao.OrderApiDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: OrderMeal
 * @description: 获取用户orders的业务逻辑
 * @author: Mr.Zhang
 * @create: 2018-06-13 18:44
 **/
public class OrderApiService {
    public OrderApiDao orderApiDao;
    public Gson gson;
    public OrderApiService(){
        this.orderApiDao=new OrderApiDao();
        this.gson=new Gson();
    }
    public String getOrdersByCusntomerId(int customerId){
        List list=orderApiDao.getOrdersByCusntomerId(customerId);
        return gson.toJson(list);
    }

    public String getOrderById(int orderId){
        Orders orders=orderApiDao.getOrderById(orderId);
        Map map=new HashMap();
        map.put("orderInfo",orders);
        map.put("imgPath",orders.getGoods().getProductPath());
        return gson.toJson(map);
    }

    public String cancelOrderById(int orderId,double goodsPrice,int customerId){
        Map map=new HashMap<>();
        boolean result=orderApiDao.cancleOrderById(orderId,goodsPrice,customerId);
        if(result){
            map.put("status",1);
            map.put("msg","取消成功!");
        }else {
            map.put("status",0);
            map.put("msg","取消失败!");
        }
        return gson.toJson(map);
    }
}
